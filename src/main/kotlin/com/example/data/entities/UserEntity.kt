package com.example.data.entities

import org.ktorm.entity.Entity
import java.time.LocalDateTime

interface UserEntity : Entity<UserEntity> {
    companion object : Entity.Factory<UserEntity>()
    val userId: Int
    val email : String
    val username: String
    val fullName:String
    val userPassword: String
    val userSalt: String
    val userSubscribe : SubscribeTypesEntity
    val profileImage: ProfileImagesEntity
    val background: UserBackgroundImageEntity
    val userRegister: LocalDateTime?
    val price : Int
    val friendsCount : Int
    val likedCount: Int
}