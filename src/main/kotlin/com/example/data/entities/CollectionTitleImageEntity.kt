package com.example.data.entities

import org.ktorm.entity.Entity

interface CollectionTitleImageEntity : Entity<CollectionTitleImageEntity> {
    companion object : Entity.Factory<CollectionTitleImageEntity>()
    val id: Int
    val url: String
    val primaryImage: Boolean
    val imagePrice: Int
    val codeName: String
}