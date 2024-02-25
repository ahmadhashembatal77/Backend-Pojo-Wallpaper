package com.example.data.entities

import org.ktorm.entity.Entity
import java.time.Instant

interface UserStickersOwnedEntity : Entity<UserStickersOwnedEntity> {
    companion object : Entity.Factory<UserStickersOwnedEntity>()
    val stickerId: StickersEntity
    val userId: UserEntity
    val register: Instant
}