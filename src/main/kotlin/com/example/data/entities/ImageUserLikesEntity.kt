package com.example.data.entities

import org.ktorm.entity.Entity
import java.time.Instant

interface ImageUserLikesEntity: Entity<ImageUserLikesEntity> {
    companion object : Entity.Factory<ImageUserLikesEntity>()

    val userId:UserEntity
    val imageId:ImageDetailsEntity
    val register: Instant
}