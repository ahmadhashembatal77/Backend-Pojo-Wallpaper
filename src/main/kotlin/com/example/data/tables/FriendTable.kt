package com.example.data.tables

import com.example.data.entities.FriendsEntity
import org.ktorm.schema.Table
import org.ktorm.schema.int

object FriendTable  : Table<FriendsEntity>("friends") {
    val idFirst = int("user_id_first").references(UserTable) { it.idFirst }
    val idSecond = int("user_id_second").references(UserTable) { it.idSecond }
}