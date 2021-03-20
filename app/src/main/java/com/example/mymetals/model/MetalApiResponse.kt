package com.example.mymetals.model

import android.os.Parcel
import android.os.Parcelable


data class MetalApiResponse(
    val code: Int,
    val rates: Rates
)