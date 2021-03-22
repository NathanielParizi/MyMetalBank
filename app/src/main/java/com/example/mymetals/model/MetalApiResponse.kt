package com.example.mymetals.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MetalApiResponse(
  @SerializedName("code")
    val code: Int,
  @SerializedName("rates")
  val rates: Rates
) : Parcelable