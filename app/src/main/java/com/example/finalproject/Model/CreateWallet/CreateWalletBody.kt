package com.example.finalproject.Model.CreateWallet

import com.squareup.moshi.Json
import java.io.Serializable

data class CreateWalletBody(
        var country: String?,
        var bank_name: String?,
        var account_number: String?,
        var routing_number: String?,
        var currency: String?,
        var dob_day: String?,
        var dob_month: String?,
        var dob_year: String?,
        var ssn_last_numbers: String?,
        var pin_code: String?
) : Serializable