package com.anb.dicodingfinal.model.player

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Player (
    @SerializedName("idPlayer")
    var idPlayer : String?,

    @SerializedName("strPlayer")
    var strPlayer : String?,

    @SerializedName("dateBorn")
    var dateBorn : String?,

    @SerializedName("strBirthLocation")
    var strBirthLocation : String?,

    @SerializedName("strPosition")
    var strPosition : String?,

    @SerializedName("strThumb")
    var strThumb : String?,

    @SerializedName("strCutout")
    var strCutout : String?,

    @SerializedName("strDescriptionEN")
    var strDescriptionEN : String?
) : Parcelable