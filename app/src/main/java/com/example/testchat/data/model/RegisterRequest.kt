package com.example.testchat.data.model

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("login")
    val login: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("password")
    val password: String
)