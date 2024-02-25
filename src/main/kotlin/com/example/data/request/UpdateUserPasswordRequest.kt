package com.example.data.request

import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserPasswordRequest(
    val username: String,
    val password: String,
    val newPassword: String,
)