package com.anb.dicodingfinal.feature.detailteam

import android.content.Context
import com.anb.dicodingfinal.feature.Base.MvpPresenter
import com.anb.dicodingfinal.feature.Base.MvpView
import com.anb.dicodingfinal.model.player.PlayerResponse
import com.anb.dicodingfinal.model.team.Team


interface DetailTeamView: MvpView {
    fun setFavoriteViewIcon(isFavorite: Boolean)
    fun setView(team: Team)
    fun showSnackbar(message: String)
    fun getCtx(): Context?
}

interface DetailTeamPresenter<V: MvpView>: MvpPresenter<V> {
    fun getPlayer(teamName: String, setPlayer: (PlayerResponse) -> Unit )
    fun isTeamFavorite()
    fun addToFavorite()
    fun removeFromFavorite()
    fun updateFavorite()
}