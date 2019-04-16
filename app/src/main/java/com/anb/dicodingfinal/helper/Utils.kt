package com.anb.dicodingfinal.helper

import android.view.View
import android.widget.ImageView
import com.anb.dicodingfinal.api.ApiRepository
import com.anb.dicodingfinal.api.TheSportDBApi
import com.anb.dicodingfinal.model.match.Match
import com.anb.dicodingfinal.model.team.Team
import com.anb.dicodingfinal.model.team.TeamResponse
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import java.text.SimpleDateFormat
import java.util.*

object Utils {
    val gson = Gson()
    val apiRepository = ApiRepository()
    private val context = CoroutineContextProvider()

    fun formatDate(dateEvent : String): String {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = format.parse(dateEvent)
        return SimpleDateFormat("EEEE, dd-MM-yyyy", Locale.getDefault())
                .format(date).toString()
    }

    fun getImageBadgeTeam(id : String, imageView : ImageView){
        getTeamDetail(id) {
            it.teams?.let { Picasso.get().load(it[0].strTeamBadge).into(imageView) }
        }
    }

    fun getTeamDetail(idTeam: String, set: (TeamResponse) -> Unit){
        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getTeamDetail(idTeam)),
                        TeamResponse::class.java
                )
            }
            set(data.await())
        }
    }

    fun semicolonReplacer(text: String): String {
        return text.replace("; ","\n")
                .replace(";","\n")
    }

    fun removeNullString(match: Match) : Match{
        with(match){
            idEvent = if (idEvent.isNullOrBlank()) "" else idEvent
            strHomeTeam = if (strHomeTeam.isNullOrBlank()) "" else strHomeTeam
            strAwayTeam = if (strAwayTeam.isNullOrBlank()) "" else strAwayTeam
            strHomeScore = if (strHomeScore.isNullOrBlank()) "" else strHomeScore
            strAwayScore = if (strAwayScore.isNullOrBlank()) "" else strAwayScore
            strHomeGoalDetails = if (strHomeGoalDetails.isNullOrBlank()) "" else strHomeGoalDetails
            strHomeRedCards = if (strHomeRedCards.isNullOrBlank()) "" else strHomeRedCards
            strHomeYellowCards = if (strHomeYellowCards.isNullOrBlank()) "" else strHomeYellowCards
            strHomeLineupGoalkeeper = if (strHomeLineupGoalkeeper.isNullOrBlank()) "" else strHomeLineupGoalkeeper
            strHomeLineupDefense = if (strHomeLineupDefense.isNullOrBlank()) "" else strHomeLineupDefense
            strHomeLineupMidfield = if (strHomeLineupMidfield.isNullOrBlank()) "" else strHomeLineupMidfield
            strHomeLineupForward = if (strHomeLineupForward.isNullOrBlank()) "" else strHomeLineupForward
            strHomeLineupSubstitutes = if (strHomeLineupSubstitutes.isNullOrBlank()) "" else strHomeLineupSubstitutes
            strAwayRedCards = if (strAwayRedCards.isNullOrBlank()) "" else strAwayRedCards
            strAwayYellowCards = if (strHomeYellowCards.isNullOrBlank()) "" else strHomeYellowCards
            strAwayLineupGoalkeeper = if (strAwayLineupGoalkeeper.isNullOrBlank()) "" else strAwayLineupGoalkeeper
            strAwayLineupDefense = if (strAwayLineupDefense.isNullOrBlank()) "" else strAwayLineupDefense
            strAwayLineupMidfield = if (strAwayLineupMidfield.isNullOrBlank()) "" else strAwayLineupMidfield
            strAwayLineupForward = if (strAwayLineupForward.isNullOrBlank()) "" else strAwayLineupForward
            strAwayLineupSubstitutes = if (strAwayLineupSubstitutes.isNullOrBlank()) "" else strAwayLineupSubstitutes
            strHomeShots = if (strHomeShots.isNullOrBlank()) "" else strHomeShots
            strAwayShots = if (strAwayShots.isNullOrBlank()) "" else strAwayShots
            dateEvent = if (dateEvent.isNullOrBlank()) "" else dateEvent
            idHomeTeam = if (idHomeTeam.isNullOrBlank()) "" else idHomeTeam
            idAwayTeam = if (idAwayTeam.isNullOrBlank()) "" else idAwayTeam
        }
        return match
    }

    fun removeTeamNullString(team: Team) : Team{
        with(team){
            idTeam = if (idTeam.isNullOrBlank()) "" else idTeam
            idSoccerXML = if (idSoccerXML.isNullOrBlank()) "" else idSoccerXML
            intLoved = if (intLoved.isNullOrBlank()) "" else intLoved
            strTeam = if (strTeam.isNullOrBlank()) "" else strTeam
            strTeamShort = if (strTeamShort.isNullOrBlank()) "" else strTeamShort
            strAlternate = if (strAlternate.isNullOrBlank()) "" else strAlternate
            intFormedYear = if (intFormedYear.isNullOrBlank()) "" else intFormedYear
            strSport = if (strSport.isNullOrBlank()) "" else strSport
            strLeague = if (strLeague.isNullOrBlank()) "" else strLeague
            idLeague = if (idLeague.isNullOrBlank()) "" else idLeague
            strDivision = if (strDivision.isNullOrBlank()) "" else strDivision
            strManager = if (strManager.isNullOrBlank()) "" else strManager
            strStadium = if (strStadium.isNullOrBlank()) "" else strStadium
            strKeywords = if (strKeywords.isNullOrBlank()) "" else strKeywords
            strRSS = if (strRSS.isNullOrBlank()) "" else strRSS
            strStadiumThumb = if (strStadiumThumb.isNullOrBlank()) "" else strStadiumThumb
            strStadiumDescription = if (strStadiumDescription.isNullOrBlank()) "" else strStadiumDescription
            strStadiumLocation = if (strStadiumLocation.isNullOrBlank()) "" else strStadiumLocation
            intStadiumCapacity = if (intStadiumCapacity.isNullOrBlank()) "" else intStadiumCapacity
            strWebsite = if (strWebsite.isNullOrBlank()) "" else strWebsite
            strFacebook = if (strFacebook.isNullOrBlank()) "" else strFacebook
            strTwitter = if (strTwitter.isNullOrBlank()) "" else strTwitter
            strInstagram = if (strInstagram.isNullOrBlank()) "" else strInstagram
            strDescriptionEN = if (strDescriptionEN.isNullOrBlank()) "" else strDescriptionEN
            strDescriptionDE = if (strDescriptionDE.isNullOrBlank()) "" else strDescriptionDE
            strDescriptionFR = if (strDescriptionFR.isNullOrBlank()) "" else strDescriptionFR
            strDescriptionCN = if (strDescriptionCN.isNullOrBlank()) "" else strDescriptionCN
            strDescriptionIT = if (strDescriptionIT.isNullOrBlank()) "" else strDescriptionIT
            strDescriptionJP = if (strDescriptionJP.isNullOrBlank()) "" else strDescriptionJP
            strDescriptionRU = if (strDescriptionRU.isNullOrBlank()) "" else strDescriptionRU
            strDescriptionES = if (strDescriptionES.isNullOrBlank()) "" else strDescriptionES
            strDescriptionPT = if (strDescriptionPT.isNullOrBlank()) "" else strDescriptionPT
            strDescriptionSE = if (strDescriptionSE.isNullOrBlank()) "" else strDescriptionSE
            strDescriptionNL = if (strDescriptionNL.isNullOrBlank()) "" else strDescriptionNL
            strDescriptionHU = if (strDescriptionHU.isNullOrBlank()) "" else strDescriptionHU
            strDescriptionNO = if (strDescriptionNO.isNullOrBlank()) "" else strDescriptionNO
            strDescriptionIL = if (strDescriptionIL.isNullOrBlank()) "" else strDescriptionIL
            strDescriptionPL = if (strDescriptionPL.isNullOrBlank()) "" else strDescriptionPL
            strGender = if (strGender.isNullOrBlank()) "" else strGender
            strCountry = if (strCountry.isNullOrBlank()) "" else strCountry
            strTeamBadge = if (strTeamBadge.isNullOrBlank()) "" else strTeamBadge
            strTeamJersey = if (strTeamJersey.isNullOrBlank()) "" else strTeamJersey
            strTeamLogo = if (strTeamLogo.isNullOrBlank()) "" else strTeamLogo
            strTeamFanart1 = if (strTeamFanart1.isNullOrBlank()) "" else strTeamFanart1
            strTeamFanart2 = if (strTeamFanart2.isNullOrBlank()) "" else strTeamFanart2
            strTeamFanart3 = if (strTeamFanart3.isNullOrBlank()) "" else strTeamFanart3
            strTeamFanart4 = if (strTeamFanart4.isNullOrBlank()) "" else strTeamFanart4
            strTeamBanner = if (strTeamBanner.isNullOrBlank()) "" else strTeamBanner
            strYoutube = if (strYoutube.isNullOrBlank()) "" else strYoutube
            strLocked = if (strLocked.isNullOrBlank()) "" else strLocked
        }
        return team
    }
}
fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}