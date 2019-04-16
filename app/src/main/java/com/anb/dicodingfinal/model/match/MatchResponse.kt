package com.anb.dicodingfinal.model.match

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MatchResponse(
        @SerializedName("events")
        @Expose
        var matchs: ArrayList<Match>? = null) : Parcelable