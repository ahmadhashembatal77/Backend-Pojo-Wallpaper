package com.example.data.request

data class UserNameAndPasswordRequest(
    val usernameOrEmail :String,
    val password:String
)