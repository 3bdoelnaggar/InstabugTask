package com.elnaggar.instabugtask.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL



data class WordCount(val word:String,val count:String)

sealed interface MainState {
    object Loading : MainState
    data class Success(val data: List<WordCount>):MainState
    data class Error(val message:String?=null) : MainState
}


class MainViewModel : ViewModel() {


    private val _stateLiveData = MutableLiveData<MainState>()
    val stateLiveData: LiveData<MainState> = _stateLiveData

    init {
        _stateLiveData.value = MainState.Loading
        kotlin.concurrent.thread {
            val site = "https://instabug.com/"
            val obj = URL(site)
            val con = obj.openConnection() as HttpURLConnection
            con.requestMethod = "GET"
            val responseCode = con.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val `in` = BufferedReader(
                    InputStreamReader(
                        con.inputStream
                    )
                )
                var inputLine: String?
                val response = StringBuffer()
                while (`in`.readLine().also { inputLine = it } != null) {
                    response.append(inputLine)
                }
                `in`.close()
                _stateLiveData.postValue(MainState.Error(response.toString()))

            } else {
                _stateLiveData.postValue(MainState.Error())

            }
        }

    }

    fun search(input: String) {

    }
}