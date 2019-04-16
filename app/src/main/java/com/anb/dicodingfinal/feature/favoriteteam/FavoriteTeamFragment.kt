package com.anb.dicodingfinal.feature.favoriteteam

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.anb.dicodingfinal.R
import com.anb.dicodingfinal.adapter.TeamAdapter
import com.anb.dicodingfinal.database.database
import com.anb.dicodingfinal.feature.detailteam.DetailTeamActivity
import com.anb.dicodingfinal.helper.Constant
import com.anb.dicodingfinal.model.team.Team
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class FavoriteTeamFragment : Fragment() {

    private lateinit var rvTeam: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout

    companion object {
        fun newInstance() : FavoriteTeamFragment{
            return FavoriteTeamFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FavoriteTeamFragmentUI().createView(AnkoContext.create(ctx,this))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)
        initView(view)

        rvTeam.layoutManager = layoutManager
        rvTeam.setHasFixedSize(true)
        getFavoriteTeam()

        swipeRefresh.onRefresh {
            getFavoriteTeam()
            swipeRefresh.isRefreshing = false
        }
    }

    private fun initView(view: View){
        swipeRefresh = view.find(R.id.srl_list_team)
        rvTeam = view.find(R.id.rv_team)
    }

    private fun getFavoriteTeam(){
        context?.database?.use {
            val result = select(Team.TABLE_TEAM)
            val favoriteTeam: ArrayList<Team> = ArrayList(result.parseList(classParser()))

            val matchScheduleAdapter = TeamAdapter(favoriteTeam){
                startActivity(intentFor<DetailTeamActivity>( Constant.TEAM to it ))
            }
            rvTeam.adapter = matchScheduleAdapter
        }

    }

    class FavoriteTeamFragmentUI: AnkoComponent<FavoriteTeamFragment> {
        override fun createView(ui: AnkoContext<FavoriteTeamFragment>) = with(ui){
            linearLayout {
                lparams (width = matchParent, height = wrapContent)
                orientation = LinearLayout.VERTICAL
                topPadding = dip(8)
                leftPadding = dip(16)
                rightPadding = dip(16)

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