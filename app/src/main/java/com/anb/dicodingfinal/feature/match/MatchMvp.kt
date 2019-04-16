package com.anb.dicodingfinal.feature.match

import com.anb.dicodingfinal.feature.Base.MvpPresenter
import com.anb.dicodingfinal.feature.Base.MvpView
import com.anb.dicodingfinal.model.league.LeagueResponse

interface MatchView : MvpView {
    fun setSpinner(leagueResponse: LeagueResponse)
    fun setTabLayout(idLeague: String)
    fun showToast(message: String)
}

interface MatchPresenter<V: MvpView>: MvpPresenter<V> {
    fun initLeaguesSpinnerData()
}