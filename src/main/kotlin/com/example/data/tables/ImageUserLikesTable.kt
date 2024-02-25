package com.example.data.tables

import com.example.data.entities.ImageUserLikesEntity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.timestamp

object ImageUserLikesTable : Table<ImageUserLikesEntity>("images_user_likes") {
    val user_id = int("user_id").references(UserTable) { it.userId }
    val image_id = int("image_id").references(ImageDetailsTable) { it.imageId }
    val user_like_register = timestamp("register").bindTo { it.register }
}
