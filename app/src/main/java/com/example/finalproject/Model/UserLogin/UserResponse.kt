package com.example.finalproject.Model.UserLogin

data class UserResponse(
        val location: Location,
        val token: String,
        val user: User,
        val vendor_details: VendorDetails
)