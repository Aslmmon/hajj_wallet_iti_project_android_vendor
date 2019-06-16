package com.example.finalproject.Model.CheckWallet


import com.squareup.moshi.Json

data class Success(
    @Json(name = "total_balance")
    val totalBalance: Int
)