package com.example.data.entities

import org.ktorm.entity.Entity
import java.time.LocalDate

interface StickersEntity: Entity<StickersEntity> {
    companion object : Entity.Factory<StickersEntity>()

    val id: Int
    val title: String
    val url: String
    val watchCount: Int
    val price: Int
    val primary: Boolean
    val register: LocalDate
    val firstLikeCount: Int
    val secondLikeCount: Int
    val thirdLikeCount: Int
    val fourthLikeCount: Int
    val fifthLikeCount: Int
    val codeName: String
}