package com.example.womensafetyapp.presentation.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.womensafetyapp.data.network.ApiClient
import com.example.womensafetyapp.data.repository.SOSRepoImpl

class HomeViewModelFactory(private val application: Application) : ViewModelProvider.Factory{
    @Suppress("NOT_CHECKED")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(
                sosRepoImpl = SOSRepoImpl(
                    apiClient = ApiClient,
                    application = application
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}