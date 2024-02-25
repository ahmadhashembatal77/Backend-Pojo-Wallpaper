package com.example.data.entities

import org.ktorm.entity.Entity

interface FriendsEntity  : Entity<FriendsEntity> {
    companion object : Entity.Factory<FriendsEntity>()
    val idFirst: UserEntity
    val idSecond: UserEntity
}