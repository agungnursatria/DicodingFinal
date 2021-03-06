package com.anb.dicodingfinal.feature.match

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.anb.dicodingfinal.R
import com.anb.dicodingfinal.adapter.MatchPagerAdapter
import com.anb.dicodingfinal.api.ApiRepository
import com.anb.dicodingfinal.helper.Utils
import com.anb.dicodingfinal.model.league.LeagueResponse
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_match.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.toast

@Suppress("DEPRECATION")
class MatchFragment : Fragment(), MatchView {
    companion object {
        fun newInstance() : MatchFragment{
            return MatchFragment()
        }
    }

    lateinit var mSectionsPagerAdapter : MatchPagerAdapter
    lateinit var searchMenu : MenuItem
    private lateinit var MPresenter : MatchPresenter<MatchView>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)

        val request = Utils.apiRepository
        val gson = Utils.gson
        MPresenter = MatchPresenterImpl(request,gson)
        MPresenter.onAttach(this)
        MPresenter.initLeaguesSpinnerData()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.search_menu, menu)
        searchMenu = menu.findItem(R.id.menu_search)
        searchMenu.isVisible = false

        val search = MenuItemCompat.getActionView(searchMenu) as SearchView
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean { return false }
            override fun onQueryTextChange(p0: String): Boolean {
                mSectionsPagerAdapter.lastMatch.search(p0)
                mSectionsPagerAdapter.nextMatch.search(p0)
                return true
            }
        })
    }

    override fun setSpinner(leagueResponse: LeagueResponse) {
        val onlySoccerLeague = leagueResponse.leagues.filter { it.strSport.equals("Soccer") }

        val spinnerItems = ArrayList<String>()
        onlySoccerLeague.forEach {
            it.strLeague?.let { it1 -> spinnerItems.add(it1) }
        }

        val spinnerAdapter = ArrayAdapter(ctx, R.layout.spinner_item, spinnerItems)
        spinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown)
        spinnerLeague.adapter = spinnerAdapter
        searchMenu.isVisible = true

        spinnerLeague.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) { }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                leagueResponse.leagues[position].idLeague?.let {
                    setTabLayout(it)
                }
            }
        }
    }

    override fun setTabLayout(idLeague: String) {
        mSectionsPagerAdapter = MatchPagerAdapter(childFragmentManager, idLeague)
        vp_match.adapter = mSectionsPagerAdapter
        tl_match.setupWithViewPager(vp_match)
    }

    override fun showToast(message: String) {
        ctx.toast(message)
    }
}