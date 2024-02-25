package com.example.data.entities

import org.ktorm.entity.Entity
import java.time.LocalDate

interface ImageDetailsEntity : Entity<ImageDetailsEntity> {
    companion object : Entity.Factory<ImageDetailsEntity>()

    val id: Int
    val imgTitle: String
    val url: String
    val categoryId: ImageCategories
    val blurHash: String
    val watchCount: Int
    val likeCount: Int
    val imagePrice: Int
    val imagePrimary: Boolean
    val register: LocalDate
}