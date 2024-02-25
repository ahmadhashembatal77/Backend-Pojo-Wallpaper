package com.example.data.entities

import org.ktorm.entity.Entity

interface ProfileImagesEntity : Entity<ProfileImagesEntity> {
    companion object : Entity.Factory<ProfileImagesEntity>()
    val id: Int
    val url: String
    val primaryImage: Boolean
    val imagePrice: Int
    val forFree: Boolean
    val codeName: String
}