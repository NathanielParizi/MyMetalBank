package com.example.mymetals.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Parcelize
data class USDXPD(
    @SerializedName("rate")
    val rate: BigDecimal,
    @SerializedName("timestamp")
    val timestamp: Int
) : Parcelable