package com.anb.dicodingfinal.model.player

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PlayerResponse (
    @SerializedName("player")
    @Expose
    var player: ArrayList<Player>? = null)