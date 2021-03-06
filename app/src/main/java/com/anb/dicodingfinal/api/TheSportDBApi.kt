package com.anb.dicodingfinal.api

import com.anb.dicodingfinal.BuildConfig

object TheSportDBApi {
    fun getTeamDetail(idTeam: String?): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupteam.php?id=" + idTeam
    }
    fun getAllLeague(): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/all_leagues.php"
    }
    fun getNextMatch(idLeague: String): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/eventsnextleague.php?id=" + idLeague
    }
    fun getPrevMatch(idLeague: String): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/eventspastleague.php?id=" + idLeague
    }
    fun getTeams(leagueName: String): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/search_all_teams.php?l=" + leagueName
    }
    fun getPlayers(teamName: String): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/searchplayers.php?t=" + teamName
    }
}