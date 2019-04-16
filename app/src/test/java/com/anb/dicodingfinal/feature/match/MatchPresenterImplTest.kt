package com.anb.dicodingfinal.feature.match

import com.anb.dicodingfinal.TestContextProvider
import com.anb.dicodingfinal.api.ApiRepository
import com.anb.dicodingfinal.api.TheSportDBApi
import com.anb.dicodingfinal.model.league.LeagueResponse
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MatchPresenterImplTest{
    @Mock
    private
    lateinit var view: MatchView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: MatchPresenter<MatchView>

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = MatchPresenterImpl(apiRepository,gson, TestContextProvider())
        presenter.onAttach(view)
    }

    @Test
    fun testInitLeagueSpinnerData(){
        val leagueResponse = LeagueResponse(arrayListOf())

        `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getAllLeague()),
                LeagueResponse::class.java
        )).thenReturn(leagueResponse)

        presenter.initLeaguesSpinnerData()

        verify(view).setSpinner(leagueResponse)
    }
}