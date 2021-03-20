package com.example.mymetals

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.mymetals.model.USDXPD
import com.example.mymetals.model.USDXPT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements


private const val TAG = "MainActivity"


class MainActivity : AppCompatActivity() {

    private var debounced = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        debounced = true
        basicCoroutineFetch()

    }


    fun basicCoroutineFetch() {

        if (debounced) {
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
                 method= span.select("span").text()
                buffer.append(method)
                prices = calculateBIDASK()
                var palladiumRate = USDXPD(prices.getBid(buffer.toString()), 0)
                buffer.delete(0, buffer.length)


                Log.d(
                    TAG,
                    "basicCoroutineFetch: ${platinumRate.rate} ${palladiumRate.rate}"
                )


                debounced = false
                debounce()
            }
        }
    }

    private suspend fun debounce() {
        delay(5000)
        debounced = true
    }


}
