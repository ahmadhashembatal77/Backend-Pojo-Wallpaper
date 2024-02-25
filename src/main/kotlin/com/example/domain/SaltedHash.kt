package com.example.domain

data class SaltedHash(
    val hash: String,
    val salt: String,
)