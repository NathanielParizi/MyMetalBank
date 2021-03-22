package com.example.mymetals.network

import android.util.Log
import com.example.mymetals.Contracts.BASE_URL
import com.example.mymetals.calculateBIDASK
import com.example.mymetals.model.MetalApiResponse
import com.example.mymetals.model.USDXPD
import com.example.mymetals.model.USDXPT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Response
import retrofit2.Retrofit

class MetalService : KoinComponent {

    private val retrofitBuilder: Retrofit.Builder by inject()
    private var apiService: IApiService = retrofitBuilder.baseUrl(BASE_URL)
        .build().create(IApiService::class.java)

    suspend fun getPairs(pairs: String): Response<MetalApiResponse> {
        return apiService.getPairs(pairs)
    }


    fun parseHTML() {
        GlobalScope.launch(Dispatchers.IO) {

            var buffer = StringBuffer()
            var doc: Document =
                Jsoup.connect("https://www.investing.com/currencies/xpt-usd").get()
            var span: Elements = doc.select("span")
            var method: String = span.select("span").text()
            buffer.append(method)
            var prices = calculateBIDASK()
            var platinumRate = USDXPT(prices.getBid(buffer.toString()), 0)
            buffer.delete(0, buffer.length)

            doc =
                Jsoup.connect("https://www.investing.com/currencies/xpd-usd").get()
            span = doc.select("span")
            method = span.select("span").text()
            buffer.append(method)
            prices = calculateBIDASK()
            var palladiumRate = USDXPD(prices.getBid(buffer.toString()), 0)
            buffer.delete(0, buffer.length)

            Log.d(
                "GOLD",
                "basicCoroutineFetch: ${platinumRate.rate} ${palladiumRate.rate}"
            )


        }
    }

}