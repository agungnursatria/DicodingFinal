package com.anb.dicodingfinal.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.anb.dicodingfinal.model.match.Match
import com.anb.dicodingfinal.model.team.Team
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "Favorites.db", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(Match.TABLE_MATCH, true,
                Match.ID_EVENT to TEXT + PRIMARY_KEY,
                Match.HOME_NAME to TEXT,
                Match.AWAY_NAME to TEXT,
                Match.HOME_SCORE to TEXT,
                Match.AWAY_SCORE to TEXT,
                Match.HOME_GOAL_DETAILS to TEXT,
                Match.HOME_RED_CARDS to TEXT,
                Match.HOME_YELLOW_CARDS to TEXT,
                Match.HOME_GOALKEEPER to TEXT,
                Match.HOME_DEFENDER to TEXT,
                Match.HOME_MIDFIELD to TEXT,
                Match.HOME_FORWARD to TEXT,
                Match.HOME_SUBTITUTE to TEXT,
                Match.AWAY_RED_CARDS to TEXT,
                Match.AWAY_YELLOW_CARDS to TEXT,
                Match.AWAY_GOAL_DETAILS to TEXT,
                Match.AWAY_GOALKEEPER to TEXT,
                Match.AWAY_DEFENDER to TEXT,
                Match.AWAY_MIDFIELD to TEXT,
                Match.AWAY_FORWARD to TEXT,
                Match.AWAY_SUBTITUTE to TEXT,
                Match.HOME_SHOTS to TEXT,
                Match.AWAY_SHOTS to TEXT,
                Match.DATE_EVENT to TEXT,
                Match.ID_HOME to TEXT,
                Match.ID_AWAY to TEXT
        )
        db.createTable(Team.TABLE_TEAM, true,
                Team.ID_TEAM to TEXT + PRIMARY_KEY,
                Team.ID_SOCCER_XML to TEXT,
                Team.LOVE to TEXT,
                Team.TEAM_NAME to TEXT,
                Team.TEAM_SHORT to TEXT,
                Team.ALTERNATE to TEXT,
                Team.FORMED_YEARS to TEXT,
                Team.SPORT to TEXT,
                Team.STR_LEAGUE to TEXT,
                Team.ID_LEAGUE to TEXT,
                Team.DIVISION to TEXT,
                Team.MANAGER to TEXT,
                Team.STADIUM to TEXT,
                Team.KEYWORD to TEXT,
                Team.RSS to TEXT,
                Team.STADIUM_THUMB to TEXT,
                Team.STADIUM_DESC to TEXT,
                Team.STADIUM_LOC to TEXT,
                Team.STADIUM_CAPACITY to TEXT,
                Team.WEBSITE to TEXT,
                Team.FACEBOOK to TEXT,
                Team.TWITTER to TEXT,
                Team.INSTAGRAM to TEXT,
                Team.DESCRIPTIONEN to TEXT,
                Team.DESCRIPTIONDE to TEXT,
                Team.DESCRIPTIONFR to TEXT,
                Team.DESCRIPTIONCN to TEXT,
                Team.DESCRIPTIONIT to TEXT,
                Team.DESCRIPTIONJP to TEXT,
                Team.DESCRIPTIONRU to TEXT,
                Team.DESCRIPTIONES to TEXT,
                Team.DESCRIPTIONPT to TEXT,
                Team.DESCRIPTIONSE to TEXT,
                Team.DESCRIPTIONNL to TEXT,
                Team.DESCRIPTIONHU to TEXT,
                Team.DESCRIPTIONNO to TEXT,
                Team.DESCRIPTIONIL to TEXT,
                Team.DESCRIPTIONPL to TEXT,
                Team.GENDER to TEXT,
                Team.COUNTRY to TEXT,
                Team.TEAM_BADGE to TEXT,
                Team.JERSEY to TEXT,
                Team.LOGO to TEXT,
                Team.TEAM_FANART1 to TEXT,
                Team.TEAM_FANART2 to TEXT,
                Team.TEAM_FANART3 to TEXT,
                Team.TEAM_FANART4 to TEXT,
                Team.TEAM_BANNER to TEXT,
                Team.YOUTUBE to TEXT,
                Team.LOCKED to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(Match.TABLE_MATCH, true)
        db.dropTable(Team.TABLE_TEAM, true)
    }
}

val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)