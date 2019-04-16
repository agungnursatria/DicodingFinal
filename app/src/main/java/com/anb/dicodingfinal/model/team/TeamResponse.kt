package com.anb.dicodingfinal.model.team

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamResponse(
        @SerializedName("teams")
        @Expose
        var teams: ArrayList<Team>? = null) : Parcelable