package com.example.data.tables

import com.example.data.entities.StickersEntity
import org.ktorm.schema.*

object StickersTable : Table<StickersEntity>(tableName = "stickers") {
    val id = int("id").bindTo { it.id }.primaryKey()
    val title = varchar("title").bindTo { it.title }
    val url = varchar("url").bindTo { it.url }
    val watchCount = int("watch_count").bindTo { it.watchCount }
    val price = int("price").bindTo { it.price }
    val primary = boolean("primary").bindTo { it.primary }
    val register = date("register").bindTo { it.register }
    val firstLikeCount = int("first_like_count").bindTo { it.firstLikeCount }
    val secondLikeCount = int("second_like_count").bindTo { it.secondLikeCount }
    val thirdLikeCount = int("third_like_count").bindTo { it.thirdLikeCount }
    val fourthLikeCount = int("fourth_like_count").bindTo { it.fourthLikeCount }
    val fifthLikeCount = int("fifth_like_count").bindTo { it.fifthLikeCount }
    val codeName = varchar("code_name").bindTo { it.codeName }
}