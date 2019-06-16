package com.example.finalproject.Model.walletCharged


import com.squareup.moshi.Json

data class SuccessWalletCharged(
    @Json(name = "success")
    val success: String
)