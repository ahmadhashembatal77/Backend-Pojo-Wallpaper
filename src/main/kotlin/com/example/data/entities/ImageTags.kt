package com.example.data.entities

import org.ktorm.entity.Entity

interface ImageTags : Entity<ImageTags> {
    companion object : Entity.Factory<ImageTags>()
    val imageId: ImageDetailsEntity
    val tagId: Tags
}