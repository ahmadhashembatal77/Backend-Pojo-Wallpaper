package com.example.data.tables

import com.example.data.entities.UserStickersOwnedEntity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.timestamp

object UserStickersOwnedTable : Table<UserStickersOwnedEntity>(tableName = "user_sticker_owned") {
    val stickerId = int("sticker_id").references(StickersTable) { it.stickerId }
    val userId = int("user_id").references(UserTable) { it.userId }
    val user_like_register = timestamp("register").bindTo { it.register }
}