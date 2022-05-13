package com.elnaggar.instabugtask.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elnaggar.instabugtask.network.Network
import java.util.regex.Matcher
import java.util.regex.Pattern


data class WordCount(val word: String, val count: String)

sealed interface MainState {
    object Loading : MainState
    data class Success(val data: List<WordCount>) : MainState
    data class Error(val message: String? = null) : MainState
}


class MainViewModel(private val network: Network) : ViewModel() {
    private val _stateLiveData = MutableLiveData<MainState>()
    val stateLiveData: LiveData<MainState> = _stateLiveData

    init {
        _stateLiveData.value = MainState.Loading
        kotlin.concurrent.thread {
            val site = "https://instabug.com/"
            val result = network.request(site)
            when (result) {
                null -> _stateLiveData.postValue(MainState.Error())
                else -> {
                    val uiResult = parseInput(result)
                    _stateLiveData.postValue(MainState.Success(uiResult))
                }

            }
        }
    }
}



