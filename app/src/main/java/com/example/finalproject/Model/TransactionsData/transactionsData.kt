package com.example.finalproject.Model.TransactionsData


import com.squareup.moshi.Json

data class transactionsData(
    @Json(name = "money_paid")
    val moneyPaid: Int,
    @Json(name = "pilgrim_id")
    val pilgrimId: Int,
    @Json(name = "pilgrim_username")
    val pilgrimUsername: String,
    @Json(name = "time_stamp")
    val timeStamp: String
)