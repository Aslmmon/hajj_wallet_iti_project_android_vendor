package com.example.finalproject.Model.TransactionsData


import com.squareup.moshi.Json

data class Error(
    @Json(name = "errors")
    val errors: List<ErrorX>
)