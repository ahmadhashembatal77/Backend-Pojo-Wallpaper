package com.example.data.tables

import com.example.data.entities.UserCollectionEntity
import org.ktorm.schema.Table
import org.ktorm.schema.boolean
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object UserCollectionTable : Table<UserCollectionEntity>("user_collections") {
    val id = int("id").bindTo { it.id }.primaryKey()
    val userId = int("user_id").references(UserTable) { it.userId }
    val collectionName = varchar("collection_name").bindTo { it.collectionName }
    val isCollectionVisible = boolean("collection_invisibility").bindTo { it.isCollectionVisible }
    val collectionUrl = int("collection_title_image").references(CollectionTitleImagesTable) {
        it.collectionImageTitle
    }
    val likesCount = int("likes_count").bindTo { it.likesCount }
    val imageNumber = int("image_number").bindTo { it.imageNumber }
}