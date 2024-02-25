package com.example.data.response

data class UserResponseWithToken(
        val userID: Int? = 0,
        val userName: String = "",
        val token: String? = "",
        val pojoPrice: Int = 0
)