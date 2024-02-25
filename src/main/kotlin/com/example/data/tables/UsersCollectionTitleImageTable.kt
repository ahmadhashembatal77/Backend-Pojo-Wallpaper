package com.example.data.tables

import com.example.data.entities.UsersCollectionTitleImageEntity
import org.ktorm.schema.Table
import org.ktorm.schema.int

object UsersCollectionTitleImageTable: Table<UsersCollectionTitleImageEntity>(
        tableName = "user_collection_title_images_owned"
) {
    val userId = int("user_id").references(UserTable) { it.userId }.primaryKey()
    val collectionTitleImageId = int("collection_title_id").references(CollectionTitleImagesTable)
    { it.collectionTitleImageId }.primaryKey()
}