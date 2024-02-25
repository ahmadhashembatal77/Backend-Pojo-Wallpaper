package com.example.data.tables

import com.example.data.entities.UserBackgroundImageEntity
import org.ktorm.schema.Table
import org.ktorm.schema.boolean
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object UserBackgroundImageTable : Table<UserBackgroundImageEntity>("user_background") {
    val id = int("id").bindTo { it.id }.primaryKey()
    val url = varchar("url").bindTo { it.url }
    val primaryImage = boolean("primary_image").bindTo { it.primaryImage }
    val imagePrice = int("image_price").bindTo { it.imagePrice }
    val forFree = boolean("for_free").bindTo { it.forFree }
    val codeName= varchar("code_name").bindTo { it.codeName }
}