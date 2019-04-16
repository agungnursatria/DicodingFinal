package com.anb.dicodingfinal.feature.team

import com.anb.dicodingfinal.feature.Base.MvpPresenter
import com.anb.dicodingfinal.feature.Base.MvpView
import com.anb.dicodingfinal.model.league.LeagueResponse
import com.anb.dicodingfinal.model.team.TeamResponse

interface TeamView: MvpView{
    fun setSpinner(leagueResponse: LeagueResponse)
    fun startRefresh()
    fun stopRefresh()
    fun setRecyclerViewTeamList(teamResponse: TeamResponse)
}

interface TeamPresenter<V: MvpView>: MvpPresenter<V> {
    fun initLeaguesSpinnerData()
    fun loadTeamList(leagueName: String, response: TeamResponse)
}