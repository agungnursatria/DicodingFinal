package com.anb.dicodingfinal.feature.Base

interface MvpPresenter<V : MvpView> {

    fun onAttach(mvpView: V)
    fun getView(): V?

}