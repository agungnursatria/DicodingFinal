package com.anb.dicodingfinal.feature.listmatch

import com.anb.dicodingfinal.api.ApiRepository
import com.anb.dicodingfinal.api.TheSportDBApi
import com.anb.dicodingfinal.feature.Base.BasePresenter
import com.anb.dicodingfinal.helper.CoroutineContextProvider
import com.anb.dicodingfinal.model.match.MatchResponse
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class ListMatchPresenterImpl<V: ListMatchView>(private var idLeague: String,
                                               private var position: Int,
                                               private val apiRepository: ApiRepository,
                                               private val gson: Gson,
                                               private val cContext: CoroutineContextProvider = CoroutineContextProvider())
    : BasePresenter<V>(), ListMatchPresenter<V> {

    override fun loadData(response: MatchResponse) {
        getView().startRefresh()
        async(cContext.main){
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(if (position == 0) TheSportDBApi.getPrevMatch(idLeague) else TheSportDBApi.getNextMatch(idLeague)),
                        MatchResponse::class.java
                )
            }
            data.await()?.let {
                response.matchs = it.matchs
                getView().setDataToRecyclerView(data.await())
            }
        }
    }

}