package com.example.mymetals.network

import com.example.mymetals.Contracts.PATH_INSTRUMENTS
import com.example.mymetals.model.MetalApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IApiService {

    @GET(PATH_INSTRUMENTS)
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("appid") key: String
    ): Response<MetalApiResponse>

}