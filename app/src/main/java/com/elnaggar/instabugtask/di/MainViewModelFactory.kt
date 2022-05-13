package com.elnaggar.instabugtask.di


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.elnaggar.instabugtask.network.Network
import com.elnaggar.instabugtask.ui.main.MainViewModel

class MainViewModelFactory(private val network: Network) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(network) as T

    }
}