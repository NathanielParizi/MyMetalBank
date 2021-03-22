package com.example.mymetals.network

import com.example.mymetals.Contracts.PATH_INSTRUMENTS
import com.example.mymetals.Contracts.QUERY_PAIRS
import com.example.mymetals.model.MetalApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface IApiService {

    @GET(PATH_INSTRUMENTS)
    suspend fun getPairs(
        @Query(QUERY_PAIRS) pairs: String,
    ): Response<MetalApiResponse>

}