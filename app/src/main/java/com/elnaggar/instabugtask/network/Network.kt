package com.elnaggar.instabugtask.network

import androidx.annotation.WorkerThread
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class Network {


    /**
     * for the sake of simplicity we will use null to denote errors,
     * in real world scenario we will have a result class that denote to success with result
     * or error with specific error
     * @param  url
     */
    @WorkerThread
    fun request(url: String): String? {
        val obj = URL(url)
        val con = obj.openConnection() as HttpURLConnection
        con.requestMethod = "GET"
        val responseCode = con.responseCode
        if (responseCode == HttpURLConnection.HTTP_OK) {
            val bufferedReader = BufferedReader(
                InputStreamReader(
                    con.inputStream
                )
            )
            var inputLine: String?
            val response = StringBuffer()
            while (bufferedReader.readLine().also { inputLine = it } != null) {
                response.append(inputLine)
            }
            bufferedReader.close()
            return response.toString()

        } else {
            return null
        }
    }
}