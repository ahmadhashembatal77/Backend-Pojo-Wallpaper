package com.example.data.entities

import org.ktorm.entity.Entity

interface UserCollectionLikeEntity: Entity<UserCollectionLikeEntity> {
    companion object : Entity.Factory<UserCollectionLikeEntity>()

    var userId:UserEntity
    var collectionId:UserCollectionEntity
}