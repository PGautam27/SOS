package com.example.womensafetyapp.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.womensafetyapp.data.dto.Contact

class LoginScreenViewModel():ViewModel() {

    private val phoneNumbers : MutableLiveData<ArrayList<Contact>> = MutableLiveData(ArrayList());
    val _phoneNumbers :LiveData<ArrayList<Contact>> = phoneNumbers

    fun addPhoneNumber(contact: Contact){
        phoneNumbers.value?.add(contact)
    }

}