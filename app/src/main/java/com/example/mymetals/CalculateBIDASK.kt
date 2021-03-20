package com.example.mymetals

class calculateBIDASK {
    private var bid: String? = null
    private var ask: String? = null
    private var commaAdjust = 0
    private var startA = 0
    private val bidBuffer = StringBuffer()
    private val askBuffer = StringBuffer()
    fun getBid(haystack: String): Double {
        var bid1 = 0.0
        val start = haystack.indexOf("Bid/Ask") + 9

        // Get bid price
        var i = start
        while (i > 0) {
            if (haystack[i] != ',') {
                if (haystack[i] == '/' && haystack[i] != ',') {
                    bid = bidBuffer.toString()
                    bid1 = bid!!.toDouble()
                    startA = i + 1
                    break
                }
                bidBuffer.append(haystack[i])
            } else {
                commaAdjust = 1
            }
            i++
        }
        commaAdjust = 0
        return bid1
    }

    fun getAsk(haystack: String): Double {

        // Get ask price
        var ask1 = 0.0
        var i = startA
        while (i > 0) {
            if (haystack[i] != ',') {
                if (Character.isLetter(haystack[i]) == true && haystack[i] != ',') {
                    ask = askBuffer.toString()
                    ask1 = ask!!.toDouble()
                    break
                }
                askBuffer.append(haystack[i])
            } else {
                commaAdjust = 1
            }
            i++
        }
        commaAdjust = 0
        return ask1
    }
}