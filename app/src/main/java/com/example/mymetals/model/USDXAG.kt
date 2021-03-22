package com.example.mymetals.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Parcelize
data class USDXAG(
    @SerializedName("rate")
    val rate: BigDecimal,
    @SerializedName("timeStamp")
    val timestamp: Int
) : Parcelable