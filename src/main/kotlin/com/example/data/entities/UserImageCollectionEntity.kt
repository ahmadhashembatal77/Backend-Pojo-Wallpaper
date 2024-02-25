package com.example.data.entities

import org.ktorm.entity.Entity

interface UserImageCollectionEntity : Entity<UserImageCollectionEntity> {
    companion object : Entity.Factory<UserImageCollectionEntity>()

    var userCollectionId: UserCollectionEntity
    var imageDetailsEntity : ImageDetailsEntity
}