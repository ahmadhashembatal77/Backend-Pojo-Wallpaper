package com.example.data.tables

import com.example.data.entities.UserEntity
import org.ktorm.schema.Table
import org.ktorm.schema.datetime
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object UserTable : Table<UserEntity>("pojo_user") {
    val userId = int(name = "id").bindTo { it.userId }.primaryKey()
    val email = varchar(name = "email").bindTo { it.email }
    val userName = varchar(name = "username").bindTo { it.username }
    val userPassword = varchar(name = "userpassword").bindTo { it.userPassword }
    val userSalt = varchar(name = "salt").bindTo { it.userSalt }
    val userSubscribe = int(name = "subscribe_id").references(SubscribeTypesTable) { it.userSubscribe }
    val userRegister = datetime(name = "register").bindTo { it.userRegister }
    val profileImage = int(name = "profile_image").references(ProfileImageTable) { it.profileImage }
    val background = int(name = "user_background").references(UserBackgroundImageTable) { it.background }
    val pojo_price = int(name = "price").bindTo { it.price }
    val friendCount = int(name = "friend_count").bindTo { it.friendsCount }
    val likedCount = int(name = "liked_count").bindTo { it.likedCount }
}