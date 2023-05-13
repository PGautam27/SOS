package com.example.womensafetyapp.data.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.womensafetyapp.data.dto.*
import com.example.womensafetyapp.data.network.ApiClient
import com.example.womensafetyapp.domain.repository.SOSRepo

class SOSRepoImpl(
    apiClient : ApiClient,
    application: Application
):SOSRepo {

    private val apiService = apiClient.getApiService(application)
    val loginResponse = MutableLiveData<LoginResponse>()
    val registerResponse = MutableLiveData<RegisterResponse>()
    val contactResponse = MutableLiveData<GetContactResponse>()

    override suspend fun login(loginRegister: LoginRegister): Boolean {
        try {
            val response = apiService.login(LoginRegister(userName = loginRegister.userName, password = loginRegister.password))
            loginResponse.value = response
            Log.v("Login",response._id.toString())
            return true
        }catch (e:Exception){
            Log.v("Login","Login Unsuccessful")
        }
        return false
    }

    override suspend fun register(loginRegister: LoginRegister):Boolean{
        try {
            val response = apiService.register(loginRegister)
            registerResponse.postValue(response)
            Log.d("Register",response.success.toString())
            return true
        }catch (e:Exception){
            Log.d("Register","Registration Unsuccessful")
        }
        return false
    }

    override suspend fun addPhoneNumber(contact: Contact, id : String): Boolean {
      try {
          val response = apiService.addPhoneNumber(id = id,contact=contact)
          return response.success
      }catch (e:Exception){

      }
        return false
    }

    override suspend fun getPhoneNumber(id: String): Boolean {
        try {
            contactResponse.value = apiService.getPhoneNumbers(id)
            Log.d("Contacts",contactResponse.value?.numberNames.toString())
            return true
        }catch (e:Exception){
            Log.d("Contacts","Didn't work")
        }
        return false
    }
}