package com.example.womensafetyapp.data.dto

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("__v")
    val __v: Int,
    @SerializedName("_id")
    val _id: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("userName")
    val userName: String
)