package com.example.data.entities

import org.ktorm.entity.Entity

interface UserImageProfileOwnedEntity  : Entity<UserImageProfileOwnedEntity> {
    companion object : Entity.Factory<UserImageProfileOwnedEntity>()

    var userId: UserEntity
    var imageProfileId : ProfileImagesEntity
}