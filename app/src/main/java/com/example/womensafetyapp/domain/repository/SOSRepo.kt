package com.example.womensafetyapp.domain.repository

import com.example.womensafetyapp.data.dto.*

interface SOSRepo {
    suspend fun login(loginRegister: LoginRegister):Boolean

    suspend fun register(loginRegister: LoginRegister) : Boolean

    suspend fun addPhoneNumber(contact: Contact, id: String):Boolean

    suspend fun getPhoneNumber(id:String):Boolean
}