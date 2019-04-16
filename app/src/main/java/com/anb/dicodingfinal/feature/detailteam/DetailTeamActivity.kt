package com.anb.dicodingfinal.feature.detailteam

import android.content.Context
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.anb.dicodingfinal.R
import com.anb.dicodingfinal.adapter.PlayerAdapter
import com.anb.dicodingfinal.api.ApiRepository
import com.anb.dicodingfinal.feature.detailplayer.DetailPlayerActivity
import com.anb.dicodingfinal.helper.Constant
import com.anb.dicodingfinal.helper.Utils
import com.anb.dicodingfinal.model.team.Team
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_team.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.intentFor

class DetailTeamActivity : AppCompatActivity(), DetailTeamView {
    private var menuDetail : Menu? = null
    private lateinit var team: Team

    lateinit var DTPresenter : DetailTeamPresenter<DetailTeamView>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val request = Utils.apiRepository
        val gson = Utils.gson
        team = intent.getParcelableExtra(Constant.TEAM)
        DTPresenter = DetailTeamPresenterImpl(team,request,gson)
        DTPresenter.onAttach(this)
        setView(team)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuDetail = menu
        DTPresenter.isTeamFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home ->{
                onBackPressed()
            }
            R.id.add_to_favorite -> {
                DTPresenter.updateFavorite()
            }
            else -> {}
        }
        return true
    }

    override fun setFavoriteViewIcon(isFavorite: Boolean) {
        menuDetail?.getItem(0)?.icon = if (isFavorite)
            ContextCompat.getDrawable(this,
                    R.drawable.ic_added_to_favorites)
        else
            ContextCompat.getDrawable(this,
                    R.drawable.ic_add_to_favorites)
    }

    override fun setView(team: Team) {
        Picasso.get().load(team.strTeamBadge).into(iv_teamBadge)
        tv_teamName.text = team.strTeam
        tv_teamYear.text = team.intFormedYear
        tv_teamStadium.text = team.strStadium
        tv_teamDesc.text = team.strDescriptionEN

        rv_listPerson.setHasFixedSize(true)
        rv_listPerson.layoutManager = LinearLayoutManager(this)
        rv_listPerson.addItemDecoration(DividerItemDecoration(rv_listPerson.context, DividerItemDecoration.VERTICAL))

        team.strTeam?.let { DTPresenter.getPlayer(it){
            it.player?.let {
                rv_listPerson.adapter = PlayerAdapter(it){
                            startActivity(intentFor<DetailPlayerActivity>( Constant.PLAYER to it ))
                }
            }
        }}
    }

    override fun showSnackbar(message: String) {
        snackbar(ll_detailTeamActivity, message).show()
    }

    override fun getCtx(): Context?{
        return this
    }

//    override fun isTeamFavorite() {
//        database.use {
//            val result = select(Team.TABLE_TEAM)
//                    .whereArgs("(ID_TEAM = {id})", "id" to idTeam)
//            val favorite = result.parseList(classParser<Team>())
//            if (!favorite.isEmpty()) isFavorite = true
//            setFavoriteViewIcon(isFavorite)
//        }
//    }
//
//    override fun removeFromFavorite() {
//        try {
//            database.use {
//                delete(Team.TABLE_TEAM,
//                        "(ID_TEAM = {id})", "id" to idTeam)
//            }
//            showSnackbar("Removed from favorite")
//        } catch (e: SQLiteConstraintException){
//            showSnackbar(e.localizedMessage)
//        }
//    }
//
//    override fun updateFavorite() {
//        if (isFavorite) removeFromFavorite() else addToFavorite()
//
//        isFavorite = !isFavorite
//        setFavoriteViewIcon(isFavorite)
//    }
//
//    override fun addToFavorite() {
//        try {
//            val teamShown = Utils.removeTeamNullString(team)
//            database.use {
//                insert(Team.TABLE_TEAM,
//                        Team.ID_TEAM to teamShown.idTeam,
//                        Team.ID_SOCCER_XML to teamShown.idSoccerXML,
//                        Team.LOVE to teamShown.intLoved,
//                        Team.TEAM_NAME to teamShown.strTeam,
//                        Team.TEAM_SHORT to teamShown.strTeamShort,
//                        Team.ALTERNATE to teamShown.strAlternate,
//                        Team.FORMED_YEARS to teamShown.intFormedYear,
//                        Team.SPORT to teamShown.strSport,
//                        Team.STR_LEAGUE to teamShown.strLeague,
//                        Team.ID_LEAGUE to teamShown.idLeague,
//                        Team.DIVISION to teamShown.strDivision,
//                        Team.MANAGER to teamShown.strManager,
//                        Team.STADIUM to teamShown.strStadium,
//                        Team.KEYWORD to teamShown.strKeywords,
//                        Team.RSS to teamShown.strRSS,
//                        Team.STADIUM_THUMB to teamShown.strStadiumThumb,
//                        Team.STADIUM_DESC to teamShown.strStadiumDescription,
//                        Team.STADIUM_LOC to teamShown.strStadiumLocation,
//                        Team.STADIUM_CAPACITY to teamShown.intStadiumCapacity,
//                        Team.WEBSITE to teamShown.strWebsite,
//                        Team.FACEBOOK to teamShown.strFacebook,
//                        Team.TWITTER to teamShown.strTwitter,
//                        Team.INSTAGRAM to teamShown.strInstagram,
//                        Team.DESCRIPTIONEN to teamShown.strDescriptionEN,
//                        Team.DESCRIPTIONDE to teamShown.strDescriptionDE,
//                        Team.DESCRIPTIONFR to teamShown.strDescriptionFR,
//                        Team.DESCRIPTIONCN to teamShown.strDescriptionCN,
//                        Team.DESCRIPTIONIT to teamShown.strDescriptionIT,
//                        Team.DESCRIPTIONJP to teamShown.strDescriptionJP,
//                        Team.DESCRIPTIONRU to teamShown.strDescriptionRU,
//                        Team.DESCRIPTIONES to teamShown.strDescriptionES,
//                        Team.DESCRIPTIONPT to teamShown.strDescriptionPT,
//                        Team.DESCRIPTIONSE to teamShown.strDescriptionSE,
//                        Team.DESCRIPTIONNL to teamShown.strDescriptionNL,
//                        Team.DESCRIPTIONHU to teamShown.strDescriptionHU,
//                        Team.DESCRIPTIONNO to teamShown.strDescriptionNO,
//                        Team.DESCRIPTIONIL to teamShown.strDescriptionIL,
//                        Team.DESCRIPTIONPL to teamShown.strDescriptionPL,
//                        Team.GENDER to teamShown.strGender,
//                        Team.COUNTRY to teamShown.strCountry,
//                        Team.TEAM_BADGE to teamShown.strTeamBadge,
//                        Team.JERSEY to teamShown.strTeamJersey,
//                        Team.LOGO to teamShown.strTeamLogo,
//                        Team.TEAM_FANART1 to teamShown.strTeamFanart1,
//                        Team.TEAM_FANART2 to teamShown.strTeamFanart2,
//                        Team.TEAM_FANART3 to teamShown.strTeamFanart3,
//                        Team.TEAM_FANART4 to teamShown.strTeamFanart4,
//                        Team.TEAM_BANNER to teamShown.strTeamBanner,
//                        Team.YOUTUBE to teamShown.strYoutube,
//                        Team.LOCKED to teamShown.strLocked
//                )
//            }
//            showSnackbar("Added to favorite")
//        } catch (e: SQLiteConstraintException){
//            showSnackbar(e.localizedMessage)
//        }
//    }
}
