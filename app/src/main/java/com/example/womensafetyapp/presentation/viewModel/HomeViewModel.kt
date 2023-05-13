package com.example.womensafetyapp.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.womensafetyapp.data.repository.SOSRepoImpl
import kotlinx.coroutines.launch

class HomeViewModel(private val sosRepoImpl: SOSRepoImpl): ViewModel() {

    val contactResponse = sosRepoImpl.contactResponse

    fun getContacts(id:String){
        viewModelScope.launch {
            sosRepoImpl.getPhoneNumber(id)
        }
    }

}