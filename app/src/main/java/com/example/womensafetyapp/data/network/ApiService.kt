package com.example.womensafetyapp.data.network

import com.example.womensafetyapp.constant.Constant
import com.example.womensafetyapp.data.dto.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST(Constant.LOGINURL)
    suspend fun login(@Body loginRegister: LoginRegister):LoginResponse

    @POST(Constant.REGISTERURL)
    suspend fun register(@Body loginRegister: LoginRegister) : RegisterResponse
    @POST(Constant.PHONUMBERURL)
    suspend fun addPhoneNumber(@Path("id") id:String,@Body contact: Contact):AddPhoneResponse

    @GET(Constant.PHONUMBERURL)
    suspend fun getPhoneNumbers(@Path("id")id:String):GetContactResponse

}