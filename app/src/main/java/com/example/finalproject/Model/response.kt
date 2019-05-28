package com.example.finalproject.Model


import com.squareup.moshi.Json

data class response(
    @Json(name = "token")
    val token: String?
)