package com.example.womensafetyapp.presentation.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LoginViewModelFactory(private val application: Application):ViewModelProvider.Factory {

    @Suppress("NOT_CHECKED")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginScreenViewModel::class.java)){
            return LoginScreenViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}