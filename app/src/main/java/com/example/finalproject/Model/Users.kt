package com.example.finalproject.Model


import com.squareup.moshi.Json

data class Users(
    @Json(name = "email")
    val email: String?,

    @Json(name = "password")
    val password: String?

)