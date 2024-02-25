package com.example.data.tables

import com.example.data.entities.UserImageProfileOwnedEntity
import org.ktorm.schema.Table
import org.ktorm.schema.int

object UserImageProfileOwnedTable : Table<UserImageProfileOwnedEntity>("user_profile_images_owned") {
    val userId = int("user_id").references(UserTable) { it.userId }.primaryKey()
    val profileImageId = int("profile_image").references(ProfileImageTable) { it.imageProfileId }.primaryKey()
}