package com.anb.dicodingfinal.feature.detailplayer

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.anb.dicodingfinal.R
import com.anb.dicodingfinal.helper.Constant
import com.anb.dicodingfinal.model.player.Player
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.activity_detail_player.*

class DetailPlayerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val player = intent.getParcelableExtra<Player>(Constant.PLAYER)
        setView(player)
    }

    @SuppressLint("SetTextI18n")
    private fun setView(player: Player?) {
        player?.let {
            with(it){
                strThumb?.let { Picasso.get().load(it).into(iv_player_pic) }
                strPlayer?.let { tv_player_name.text = it }
                strPosition?.let { tv_player_position.text = it }

                var birth = ""
                strBirthLocation?.let { birth = "$it," }
                dateBorn?.let { tv_player_born.text = birth + it }
                strDescriptionEN?.let { tv_player_desc.text = it }
            }
        }
    }

}
