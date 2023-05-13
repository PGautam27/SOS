package com.example.womensafetyapp.presentation.viewModel

import android.util.Log
import androidx.lifecycle.*
import com.example.womensafetyapp.data.dto.Contact
import com.example.womensafetyapp.data.dto.LoginRegister
import com.example.womensafetyapp.data.dto.LoginResponse
import com.example.womensafetyapp.data.dto.RegisterResponse
import com.example.womensafetyapp.data.repository.SOSRepoImpl
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class LoginScreenViewModel(private val sosRepoImpl: SOSRepoImpl):ViewModel() {

    private val phoneNumbers : MutableLiveData<ArrayList<Contact>> = MutableLiveData(ArrayList());
    val _phoneNumbers :LiveData<ArrayList<Contact>> = phoneNumbers

    val registerResponse : LiveData<RegisterResponse> = sosRepoImpl.registerResponse
    val loginResponse : LiveData<LoginResponse> = sosRepoImpl.loginResponse
    private var id: String? = null

    fun getIdValue(): String? {
        return id
    }

    fun idValue(value: String) {
        id = value
    }

    private val _error = MutableLiveData<String>()
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        _error.value = "Server is unreachable"
    }

    private val registerResponse1 = MutableLiveData<Boolean>(false)
    val _registerResponse1 : LiveData<Boolean> = registerResponse1

    private val addPhoneDone = MutableLiveData<Boolean>(false)
    val _addPhoneDone : LiveData<Boolean> = addPhoneDone

    private val loginValue = MutableLiveData<Boolean>(false)
    val _loginValue : LiveData<Boolean> = loginValue
    fun addPhoneNumber(contact: Contact){
        phoneNumbers.value?.add(contact)
    }

    fun sendPhoneNumber(){
        viewModelScope.launch {
            _phoneNumbers.value!!.forEach(){
                Log.d("Contact send",it.phone+" "+it.name)
                sosRepoImpl.addPhoneNumber(contact = Contact(name = it.name, phone = it.phone), id = registerResponse.value!!.id!!)
            }
            addPhoneDone.value = true
        }
    }

    fun register(userName: String, password: String) : Boolean {
        viewModelScope.launch(exceptionHandler) {
            val x = sosRepoImpl.register(LoginRegister(password = password, userName = userName))
            registerResponse1.value = x
        }
        return if (_registerResponse1.value==null){
            false
        }else _registerResponse1.value!!
    }

    fun login(userName: String,password: String){
        viewModelScope.launch {
            val x = sosRepoImpl.login(LoginRegister(userName = userName, password = password))
            loginValue.value = x
            idValue(loginResponse.value?._id.toString())
            Log.d("Login id login",getIdValue().toString())
        }
    }
}