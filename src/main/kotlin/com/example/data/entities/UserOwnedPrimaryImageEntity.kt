package com.example.data.entities

import org.ktorm.entity.Entity
import java.time.Instant

interface UserOwnedPrimaryImageEntity: Entity<UserOwnedPrimaryImageEntity> {
    companion object : Entity.Factory<UserOwnedPrimaryImageEntity>()

    val userId: UserEntity
    val imageId: ImageDetailsEntity
    val addTime: Instant
}