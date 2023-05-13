package com.example.womensafetyapp.data.dto

import com.google.gson.annotations.SerializedName

data class LoginRegister(

    @SerializedName("password")
    val password : String,
    @SerializedName("userName")
    val userName : String,
)

