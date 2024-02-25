package com.example.data.entities

import org.ktorm.entity.Entity
import java.time.Instant

interface UserLikedStickerEntity : Entity<UserLikedStickerEntity> {
    companion object : Entity.Factory<UserLikedStickerEntity>()
    val userId:UserEntity
    val stickerId:StickersEntity
    val register: Instant
}