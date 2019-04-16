package com.anb.dicodingfinal.feature.favoritematch

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
import com.anb.dicodingfinal.database.database
import com.anb.dicodingfinal.feature.detail.DetailActivity
import com.anb.dicodingfinal.helper.Constant
import com.anb.dicodingfinal.model.match.Match
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class FavoriteMatchFragment : Fragment() {
    private lateinit var rvMatch : RecyclerView
    private lateinit var srl_Match : SwipeRefreshLayout

    companion object {
        fun newInstance() : FavoriteMatchFragment{
            return FavoriteMatchFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FavoriteMatchFragmentUI().createView(AnkoContext.create(ctx,this))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)
        initView(view)

        rvMatch.layoutManager = layoutManager
        rvMatch.setHasFixedSize(true)
        getFavoriteMatch()

        srl_Match.onRefresh {
            getFavoriteMatch()
            srl_Match.isRefreshing = false
        }
    }
    private fun getFavoriteMatch(){
        context?.database?.use {
            val result = select(Match.TABLE_MATCH)
            val favoriteMatch: ArrayList<Match> = ArrayList(result.parseList(classParser()))

            val matchScheduleAdapter = MatchScheduleAdapter(favoriteMatch){
                startActivity(intentFor<DetailActivity>( Constant.MATCH to it ))
            }
            rvMatch.adapter = matchScheduleAdapter
        }
    }

    private fun initView(view: View){
        rvMatch = view.find(R.id.rv_list_match)
        srl_Match = view.find(R.id.srl_list_match)
    }

    class FavoriteMatchFragmentUI: AnkoComponent<FavoriteMatchFragment> {
        override fun createView(ui: AnkoContext<FavoriteMatchFragment>) = with(ui){
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