package com.example.data.tables

import com.example.data.entities.CollectionTitleImageEntity
import org.ktorm.schema.Table
import org.ktorm.schema.boolean
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object CollectionTitleImagesTable : Table<CollectionTitleImageEntity>("collections_title_image") {
    val id = int("id").bindTo { it.id }.primaryKey()
    val url = varchar("url").bindTo { it.url }
    val primaryImage = boolean("primary_image").bindTo { it.primaryImage }
    val imagePrice = int("image_price").bindTo { it.imagePrice }
    val codeName = varchar("code_name").bindTo { it.codeName }
}