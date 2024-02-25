package com.example.data.tables

import com.example.data.entities.UserOwnedPrimaryImageEntity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.timestamp

object UserOwnedPrimaryImageTable : Table<UserOwnedPrimaryImageEntity>(tableName = "user_primary_image_owned") {
    val user_id = int("user_id").references(UserTable) { it.userId }
    val image_id = int("image_id").references(ImageDetailsTable) { it.imageId }
    val user_owned_time = timestamp("add_time").bindTo { it.addTime }
}