package com.example.finalproject.Model.TransactionsData


import com.squareup.moshi.Json

data class ErrorX(
    @Json(name = "field")
    val `field`: String,
    @Json(name = "message")
    val message: String
)