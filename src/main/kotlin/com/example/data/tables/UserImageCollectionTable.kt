package com.example.data.tables

import com.example.data.entities.UserImageCollectionEntity
import org.ktorm.schema.Table
import org.ktorm.schema.int

object UserImageCollectionTable : Table<UserImageCollectionEntity>("user_image_collection") {
    val collection_id = int("collection_id").references(UserCollectionTable) { it.userCollectionId }
    val image_id = int("image_id").references(ImageDetailsTable) { it.imageDetailsEntity }
}