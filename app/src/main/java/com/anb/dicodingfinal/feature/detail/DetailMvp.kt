package com.anb.dicodingfinal.feature.detail

import android.content.Context
import com.anb.dicodingfinal.feature.Base.MvpPresenter
import com.anb.dicodingfinal.feature.Base.MvpView
import com.anb.dicodingfinal.model.match.Match

interface DetailView: MvpView{
    fun setFavoriteViewIcon(isFavorite: Boolean)
    fun setView(match: Match)
    fun showSnackbar(message: String)
    fun getCtx(): Context?
}

interface DetailPresenter<V: MvpView>: MvpPresenter<V>{
    fun isMatchFavorite()
    fun addToFavorite()
    fun removeFromFavorite()
    fun updateFavorite()
}