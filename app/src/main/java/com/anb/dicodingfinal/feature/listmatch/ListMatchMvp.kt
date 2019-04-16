package com.anb.dicodingfinal.feature.listmatch

import android.content.Context
import android.view.View
import com.anb.dicodingfinal.feature.Base.MvpPresenter
import com.anb.dicodingfinal.feature.Base.MvpView
import com.anb.dicodingfinal.model.match.MatchResponse

interface ListMatchView: MvpView {
    fun initView(view: View)
    fun setDataToRecyclerView(matchResponse: MatchResponse)
    fun showToast(message: String)
    fun startRefresh()
    fun stopRefresh()
    fun getCtx(): Context?
}

interface ListMatchPresenter<V: MvpView>: MvpPresenter<V> {
    fun loadData(response: MatchResponse)
}