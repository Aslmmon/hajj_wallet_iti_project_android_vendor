package com.example.finalproject.Model.CheckWallet


import com.squareup.moshi.Json

data class walletExistenceResponse(
    @Json(name = "success")
    val success: Success
)