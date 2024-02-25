package com.example.data.entities

import org.ktorm.entity.Entity

interface StickersCollectionStickersEntity : Entity<StickersCollectionStickersEntity> {
    companion object : Entity.Factory<StickersCollectionStickersEntity>()
    val stickerId: StickersEntity
    val collectionId: StickersCollectionsEntity
}