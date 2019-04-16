package com.anb.dicodingfinal.feature.listmatch

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anb.dicodingfinal.R
import com.anb.dicodingfinal.adapter.MatchScheduleAdapter
import com.anb.dicodingfinal.api.ApiRepository
import com.anb.dicodingfinal.helper.Constant
import com.anb.dicodingfinal.model.match.MatchResponse
import com.anb.dicodingfinal.feature.detail.DetailActivity
import com.anb.dicodingfinal.helper.Utils
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.*

class ListMatchFragment : Fragment(), ListMatchView {
    lateinit var rvMatch : RecyclerView
    lateinit var swipeRefreshLayout : SwipeRefreshLayout
    lateinit var LMPresenter : ListMatchPresenter<ListMatchView>
    var fullMatchResponse: MatchResponse = MatchResponse(arrayListOf())
    var nameSearch = ""

    companion object {

        fun newInstance(id: String, position: Int) : ListMatchFragment {
            val bundle = Bundle()
            bundle.putString(Constant.ID_LEAGUE, id)
            bundle.putInt(Constant.POSITION, position)

            val fragment = ListMatchFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ListMatchFragmentUI().createView(AnkoContext.create(ctx,this))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val idLeague = arguments?.getString(Constant.ID_LEAGUE) ?: ""
        val position = arguments?.getInt(Constant.POSITION) ?: 0

        initView(view)
        val request = Utils.apiRepository
        val gson = Utils.gson
        LMPresenter = ListMatchPresenterImpl(idLeague, position, request, gson)
        LMPresenter.onAttach(this)
        LMPresenter.loadData(fullMatchResponse)

        val layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)
        rvMatch.layoutManager = layoutManager

        swipeRefreshLayout.onRefresh {
            if (nameSearch.isNotBlank() && nameSearch.isNotEmpty()){
                search(nameSearch)
            }else{
                LMPresenter.loadData(fullMatchResponse)
            }
        }
    }

    fun search(name: String){
        nameSearch = name
        if (name.isNotBlank() && name.isNotEmpty()){
            val filteredMatch = fullMatchResponse.matchs?.filter {
                match -> match.strHomeTeam?.toLowerCase()?.contains(name.toLowerCase()) ?:  false || match.strAwayTeam?.toLowerCase()?.contains(name.toLowerCase()) ?: false
            }
            setDataToRecyclerView(MatchResponse(ArrayList(filteredMatch)))
        } else {
            setDataToRecyclerView(fullMatchResponse)
        }
    }

    override fun setDataToRecyclerView(matchResponse: MatchResponse) {
        matchResponse.matchs?.let {
            val matchScheduleAdapter = MatchScheduleAdapter(it){
                startActivity(intentFor<DetailActivity>( Constant.MATCH to it ))
            }
            rvMatch.adapter = matchScheduleAdapter
        }

        stopRefresh()
    }

    override fun initView(view: View) {
        rvMatch = view.find(R.id.rv_list_match)
        swipeRefreshLayout = view.find(R.id.srl_list_match)
    }

    override fun showToast(message: String) {
        toast(message)
    }

    override fun startRefresh() {
        swipeRefreshLayout.isRefreshing = true
    }

    override fun stopRefresh() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun getCtx(): Context?{
        return context
    }

    class ListMatchFragmentUI: AnkoComponent<ListMatchFragment>{
        override fun createView(ui: AnkoContext<ListMatchFragment>) = with(ui){
            swipeRefreshLayout {
                id = R.id.srl_list_match
                recyclerView {
                    id = R.id.rv_list_match
                    lparams (width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
                }
            }
        }
    }
}
