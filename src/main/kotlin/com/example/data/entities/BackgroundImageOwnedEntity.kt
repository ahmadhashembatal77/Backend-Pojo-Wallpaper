package com.example.data.entities

import org.ktorm.entity.Entity

interface BackgroundImageOwnedEntity  : Entity<BackgroundImageOwnedEntity> {
    companion object : Entity.Factory<BackgroundImageOwnedEntity>()

    var userId: UserEntity
    var backgroundImageId : UserBackgroundImageEntity
}