package com.example.data.tables

import com.example.data.entities.UserCollectionLikeEntity
import org.ktorm.schema.Table
import org.ktorm.schema.int

object UserCollectionsLikeTable : Table<UserCollectionLikeEntity>("user_collection_likes") {
    val user_id = int("user_id").references(UserTable) { it.userId }
    val collection_id = int("collection_id").references(UserCollectionTable) { it.collectionId }
}