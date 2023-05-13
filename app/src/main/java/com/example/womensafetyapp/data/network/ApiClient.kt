package com.example.womensafetyapp.data.network

import android.app.Application
import com.example.womensafetyapp.constant.Constant
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private lateinit var apiService : ApiService

    fun getApiService(application: Application):ApiService{
        val retrofit = Retrofit.Builder()
            .baseUrl(Constant.PRODUCTIONBASEURL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor()).build())
            .build()

        apiService = retrofit.create(ApiService::class.java)
        return apiService
    }
}