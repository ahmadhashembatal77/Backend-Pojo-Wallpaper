package com.example.data.entities

import org.ktorm.entity.Entity
import java.time.LocalDate

interface StickersCollectionsEntity : Entity<StickersCollectionsEntity> {
    companion object : Entity.Factory<StickersCollectionsEntity>()
    val id: Int
    val name: String
    val url: String
    val date: LocalDate
}