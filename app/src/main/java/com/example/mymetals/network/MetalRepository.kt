package com.example.mymetals.network

import com.example.mymetals.model.MetalApiResponse
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Response

class MetalRepository : KoinComponent {


    private val magicService: MetalService by inject()

    suspend fun getPairs(
        country: String,
    ): Response<MetalApiResponse> {
        return magicService.getPairs(country)
    }

    suspend fun getExotics(){
         magicService.parseHTML()
    }
}