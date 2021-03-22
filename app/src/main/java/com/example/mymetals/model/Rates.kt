package com.example.mymetals.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rates(
    @SerializedName("USDXAG")
    val USDXAG: USDXAG,
    @SerializedName("USDXAU")
    val USDXAU: USDXAU
) : Parcelable