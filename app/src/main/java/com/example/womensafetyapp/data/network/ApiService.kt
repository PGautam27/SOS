package com.example.womensafetyapp.data.network

import com.example.womensafetyapp.data.dto.GetContactResponse
import com.example.womensafetyapp.data.dto.LoginResponse
import com.example.womensafetyapp.data.dto.RegisterResponse
import retrofit2.http.GET

interface ApiService {

    suspend fun login():LoginResponse

    suspend fun register():RegisterResponse

    suspend fun addPhoneNumber()

    suspend fun getPhoneNumbers():GetContactResponse

}