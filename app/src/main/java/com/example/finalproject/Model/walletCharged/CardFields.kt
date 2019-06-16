package com.example.finalproject.Model.walletCharged

data class CardFields(val card_number:String,
                      val exp_month:Int,
                      val exp_year:Int,
                      val cvc:Int,
                      val amount:String,
                      val currency:String,
                      val pin_code:Int)