package com.anb.dicodingfinal.feature.match

import com.anb.dicodingfinal.api.ApiRepository
import com.anb.dicodingfinal.api.TheSportDBApi
import com.anb.dicodingfinal.feature.Base.BasePresenter
import com.anb.dicodingfinal.helper.CoroutineContextProvider
import com.anb.dicodingfinal.model.league.LeagueResponse
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class MatchPresenterImpl<V: MatchView>(private val apiRepository: ApiRepository,
                                       private val gson: Gson,
                                       private val context: CoroutineContextProvider = CoroutineContextProvider())
    : BasePresenter<V>(), MatchPresenter<V>{

    override fun initLeaguesSpinnerData() {
        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getAllLeague()),
                        LeagueResponse::class.java
                )
            }
            getView().setSpinner(data.await())
        }
    }

}