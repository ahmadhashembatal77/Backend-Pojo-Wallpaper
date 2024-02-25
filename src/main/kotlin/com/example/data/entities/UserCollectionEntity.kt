package com.example.data.entities

import org.ktorm.entity.Entity

interface UserCollectionEntity : Entity<UserCollectionEntity> {
    companion object : Entity.Factory<UserCollectionEntity>()

    val id: Int
    val userId: UserEntity
    val collectionName: String
    val isCollectionVisible: Boolean
    val likesCount: Int
    val collectionImageTitle : CollectionTitleImageEntity
    val imageNumber: Int
}