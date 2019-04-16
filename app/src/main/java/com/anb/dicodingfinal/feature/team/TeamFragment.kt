package com.anb.dicodingfinal.feature.team

import android.R.layout.simple_spinner_dropdown_item
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.MenuItemCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Spinner
import com.anb.dicodingfinal.api.ApiRepository
import com.anb.dicodingfinal.R
import com.anb.dicodingfinal.adapter.TeamAdapter
import com.anb.dicodingfinal.feature.detailteam.DetailTeamActivity
import com.anb.dicodingfinal.helper.Constant
import com.anb.dicodingfinal.helper.Utils
import com.anb.dicodingfinal.model.league.LeagueResponse
import com.anb.dicodingfinal.model.team.TeamResponse
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.*

@Suppress("DEPRECATION")
class TeamFragment : Fragment(), TeamView {

    companion object {
        fun newInstance() : TeamFragment {
            return TeamFragment()
        }
    }

    private lateinit var TPresenter: TeamPresenter<TeamView>
    private lateinit var rvTeam: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner
    var fullTeamResponse: TeamResponse = TeamResponse(arrayListOf())
    var leagueName = ""
    var nameSearch = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return TeamFragmentUI().createView(AnkoContext.create(ctx,this))
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        initView()

        val request = Utils.apiRepository
        val gson = Utils.gson
        TPresenter = TeamPresenterImpl(request, gson)
        TPresenter.onAttach(this)
        TPresenter.initLeaguesSpinnerData()

        val layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)
        rvTeam.layoutManager = layoutManager

        swipeRefresh.onRefresh {
            if (nameSearch.isNotBlank() && nameSearch.isNotEmpty()){
                search(nameSearch)
            } else {
                TPresenter.loadTeamList(leagueName, fullTeamResponse)
            }
        }
    }

    private fun initView(){
        swipeRefresh = find(R.id.srl_list_team)
        spinner = find(R.id.spinnerLeagueTeam)
        rvTeam = find(R.id.rv_team)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.search_menu, menu)

        val searchMenu = menu.findItem(R.id.menu_search)
        val search = MenuItemCompat.getActionView(searchMenu) as SearchView
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean { return false }
            override fun onQueryTextChange(p0: String): Boolean {
                search(p0)
                return true
            }
        })
    }

    fun search(name: String){
        nameSearch = name
        if (name.isNotBlank() && name.isNotEmpty()){
            val filteredMatch = fullTeamResponse.teams?.filter {
                match -> match.strTeam?.toLowerCase()?.contains(name.toLowerCase()) ?:  false
            }
            setRecyclerViewTeamList(TeamResponse(ArrayList(filteredMatch)))
        } else {
            setRecyclerViewTeamList(fullTeamResponse)
        }
    }

    override fun setSpinner(leagueResponse: LeagueResponse) {
        val onlySoccerLeague = leagueResponse.leagues.filter { it.strSport.equals("Soccer") }

        val spinnerItems = ArrayList<String>()
        onlySoccerLeague.forEach {
            it.strLeague?.let { it1 -> spinnerItems.add(it1) }
        }

        val spinnerAdapter = ArrayAdapter(ctx, simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) { }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                leagueName = spinner.selectedItem.toString()
                TPresenter.loadTeamList(leagueName, fullTeamResponse)
            }
        }
    }

    override fun setRecyclerViewTeamList(teamResponse: TeamResponse) {
        teamResponse.teams?.let {
            val teamAdapter = TeamAdapter(it){
                startActivity(intentFor<DetailTeamActivity>( Constant.TEAM to it ))
            }
            rvTeam.adapter = teamAdapter
        }

        stopRefresh()
    }

    override fun startRefresh() {
        swipeRefresh.isRefreshing = true
    }

    override fun stopRefresh() {
        swipeRefresh.isRefreshing = false
    }

    class TeamFragmentUI: AnkoComponent<TeamFragment> {
        override fun createView(ui: AnkoContext<TeamFragment>) = with(ui){
            linearLayout {
                lparams (width = matchParent, height = wrapContent)
                orientation = LinearLayout.VERTICAL
                topPadding = dip(16)
                leftPadding = dip(16)
                rightPadding = dip(16)

                spinner {
                    id = R.id.spinnerLeagueTeam
                }
                swipeRefreshLayout {
                    id = R.id.srl_list_team
                    recyclerView {
                        id = R.id.rv_team
                        lparams (width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }
                }
            }
        }
    }
}