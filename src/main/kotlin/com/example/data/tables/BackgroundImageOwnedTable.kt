package com.example.data.tables

import com.example.data.entities.BackgroundImageOwnedEntity
import org.ktorm.schema.Table
import org.ktorm.schema.int

object BackgroundImageOwnedTable  : Table<BackgroundImageOwnedEntity>("user_background_owned") {
    val userId = int("user_id").references(UserTable) { it.userId }.primaryKey()
    val backgroundImageId = int("background_id").references(UserBackgroundImageTable) { it.backgroundImageId }.primaryKey()
}