package com.example.data.tables

import com.example.data.entities.UserLikedStickerEntity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.timestamp

object UserLikedStickersTable : Table<UserLikedStickerEntity>("user_liked_sticker") {
    val user_id = int("user_id").references(UserTable) { it.userId }
    val sticker_id = int("sticker_id").references(StickersTable) { it.stickerId }
    val user_like_register = timestamp("register").bindTo { it.register }
}