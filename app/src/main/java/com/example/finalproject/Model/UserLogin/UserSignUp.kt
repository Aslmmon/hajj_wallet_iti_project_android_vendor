package com.example.finalproject.Model.UserLogin

import java.io.Serializable

data class UserSignUp(
        var email: String?,
        var username: String?,
        var first_name: String?,
        var last_name: String?,
        var phone_number: String?,
        var gender: String?,
        var nationality: String?,
        var category: String?,
        var crn: String?,
        var code: String?,
        var password1: String?,
        var password2: String?,
        var store_name: String?,
        var location:String?
) : Serializable