package com.example.womensafetyapp.domain.repository

import com.example.womensafetyapp.data.dto.*

interface SOSRepo {
    suspend fun login(loginRegister: LoginRegister):LoginResponse?

    suspend fun register(loginRegister: LoginRegister):RegisterResponse

    suspend fun addPhoneNumber(contact: Contact):RegisterResponse

    suspend fun getPhoneNumber(id:String):GetContactResponse
}