package com.example.mymetals

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.appcompat.app.AppCompatActivity
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
                val buffer = StringBuffer()
                val doc: Document =
                    Jsoup.connect("https://www.investing.com/currencies/xpt-usd").get()
                val span: Elements = doc.select("span")
                val method: String = span.select("span").text()
                buffer.append(method)
                val prices = calculateBIDASK()

                Log.d(
                    "GOLD",
                    "onCreate: ${prices.getBid(buffer.toString())}"
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
