package com.anb.dicodingfinal.feature.detailteam

import android.database.sqlite.SQLiteConstraintException
import com.anb.dicodingfinal.api.ApiRepository
import com.anb.dicodingfinal.api.TheSportDBApi
import com.anb.dicodingfinal.database.database
import com.anb.dicodingfinal.feature.Base.BasePresenter
import com.anb.dicodingfinal.helper.CoroutineContextProvider
import com.anb.dicodingfinal.helper.Utils
import com.anb.dicodingfinal.model.player.PlayerResponse
import com.anb.dicodingfinal.model.team.Team
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class DetailTeamPresenterImpl<V: DetailTeamView>(var team: Team,
                                                 private val apiRepository: ApiRepository,
                                                 private val gson: Gson,
                                                 private val context: CoroutineContextProvider = CoroutineContextProvider())
    : BasePresenter<V>(), DetailTeamPresenter<V>{

    private var isFavorite = false

    override fun getPlayer(teamName: String, setPlayer: (PlayerResponse) -> Unit ) {
        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getPlayers(teamName)),
                        PlayerResponse::class.java
                )
            }
            setPlayer(data.await())
        }
    }

    override fun isTeamFavorite() {
        getView().getCtx()?.database?.use {
            team.idTeam?.let {
                val result = select(Team.TABLE_TEAM)
                        .whereArgs("(ID_TEAM = {id})", "id" to it)
                val favorite = result.parseList(classParser<Team>())
                if (!favorite.isEmpty()) isFavorite = true
                getView().setFavoriteViewIcon(isFavorite)
            }
        }
    }

    override fun addToFavorite() {
        try {
            val teamShown = Utils.removeTeamNullString(team)
            getView().getCtx()?.database?.use {
                insert(Team.TABLE_TEAM,
                        Team.ID_TEAM to teamShown.idTeam,
                        Team.ID_SOCCER_XML to teamShown.idSoccerXML,
                        Team.LOVE to teamShown.intLoved,
                        Team.TEAM_NAME to teamShown.strTeam,
                        Team.TEAM_SHORT to teamShown.strTeamShort,
                        Team.ALTERNATE to teamShown.strAlternate,
                        Team.FORMED_YEARS to teamShown.intFormedYear,
                        Team.SPORT to teamShown.strSport,
                        Team.STR_LEAGUE to teamShown.strLeague,
                        Team.ID_LEAGUE to teamShown.idLeague,
                        Team.DIVISION to teamShown.strDivision,
                        Team.MANAGER to teamShown.strManager,
                        Team.STADIUM to teamShown.strStadium,
                        Team.KEYWORD to teamShown.strKeywords,
                        Team.RSS to teamShown.strRSS,
                        Team.STADIUM_THUMB to teamShown.strStadiumThumb,
                        Team.STADIUM_DESC to teamShown.strStadiumDescription,
                        Team.STADIUM_LOC to teamShown.strStadiumLocation,
                        Team.STADIUM_CAPACITY to teamShown.intStadiumCapacity,
                        Team.WEBSITE to teamShown.strWebsite,
                        Team.FACEBOOK to teamShown.strFacebook,
                        Team.TWITTER to teamShown.strTwitter,
                        Team.INSTAGRAM to teamShown.strInstagram,
                        Team.DESCRIPTIONEN to teamShown.strDescriptionEN,
                        Team.DESCRIPTIONDE to teamShown.strDescriptionDE,
                        Team.DESCRIPTIONFR to teamShown.strDescriptionFR,
                        Team.DESCRIPTIONCN to teamShown.strDescriptionCN,
                        Team.DESCRIPTIONIT to teamShown.strDescriptionIT,
                        Team.DESCRIPTIONJP to teamShown.strDescriptionJP,
                        Team.DESCRIPTIONRU to teamShown.strDescriptionRU,
                        Team.DESCRIPTIONES to teamShown.strDescriptionES,
                        Team.DESCRIPTIONPT to teamShown.strDescriptionPT,
                        Team.DESCRIPTIONSE to teamShown.strDescriptionSE,
                        Team.DESCRIPTIONNL to teamShown.strDescriptionNL,
                        Team.DESCRIPTIONHU to teamShown.strDescriptionHU,
                        Team.DESCRIPTIONNO to teamShown.strDescriptionNO,
                        Team.DESCRIPTIONIL to teamShown.strDescriptionIL,
                        Team.DESCRIPTIONPL to teamShown.strDescriptionPL,
                        Team.GENDER to teamShown.strGender,
                        Team.COUNTRY to teamShown.strCountry,
                        Team.TEAM_BADGE to teamShown.strTeamBadge,
                        Team.JERSEY to teamShown.strTeamJersey,
                        Team.LOGO to teamShown.strTeamLogo,
                        Team.TEAM_FANART1 to teamShown.strTeamFanart1,
                        Team.TEAM_FANART2 to teamShown.strTeamFanart2,
                        Team.TEAM_FANART3 to teamShown.strTeamFanart3,
                        Team.TEAM_FANART4 to teamShown.strTeamFanart4,
                        Team.TEAM_BANNER to teamShown.strTeamBanner,
                        Team.YOUTUBE to teamShown.strYoutube,
                        Team.LOCKED to teamShown.strLocked
                )
            }
            getView().showSnackbar("Added to favorite")
        } catch (e: SQLiteConstraintException){
            getView().showSnackbar(e.localizedMessage)
        }
    }

    override fun removeFromFavorite() {
        try {
            getView().getCtx()?.database?.use {
                team.idTeam?.let {
                    delete(Team.TABLE_TEAM,
                            "(ID_TEAM = {id})", "id" to it)
                }
            }
            getView().showSnackbar("Removed from favorite")
        } catch (e: SQLiteConstraintException){
            getView().showSnackbar(e.localizedMessage)
        }
    }

    override fun updateFavorite() {
        if (isFavorite) removeFromFavorite() else addToFavorite()

        isFavorite = !isFavorite
        getView().setFavoriteViewIcon(isFavorite)
    }


}