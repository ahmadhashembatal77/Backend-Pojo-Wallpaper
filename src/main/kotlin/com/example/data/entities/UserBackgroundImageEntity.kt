package com.example.data.entities

import org.ktorm.entity.Entity

interface UserBackgroundImageEntity : Entity<UserBackgroundImageEntity> {
    companion object : Entity.Factory<UserBackgroundImageEntity>()
    val id: Int
    val url: String
    val primaryImage: Boolean
    val imagePrice: Int
    val forFree: Boolean
    val codeName: String
}