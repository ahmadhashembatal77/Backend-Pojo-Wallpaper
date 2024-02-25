package com.example.data.entities

import org.ktorm.entity.Entity

interface UsersCollectionTitleImageEntity  : Entity<UsersCollectionTitleImageEntity> {
    companion object : Entity.Factory<UsersCollectionTitleImageEntity>()

    var userId: UserEntity
    var collectionTitleImageId : CollectionTitleImageEntity
}