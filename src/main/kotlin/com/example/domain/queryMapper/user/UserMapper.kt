package com.example.domain.queryMapper.user

import com.example.data.dto.user.*
import com.example.data.tables.*
import org.ktorm.dsl.QueryRowSet
import org.ktorm.dsl.isNotNull

fun QueryRowSet.toUserDetailsDto() = UserDto(
        user_id = this[UserTable.userId] ?: 0,
        user_name = this[UserTable.userName] ?: "0",
        user_email = this[UserTable.email] ?: "0",
        subscribe_details = this.toSubscribeTypeDto(),
        user_background = this.toUserBackgroundDto(),
        user_profile = this.toUserProfileImageDto(),
        friend_count = this[UserTable.friendCount] ?: 0,
        pojo_price = this[UserTable.pojo_price] ?: 100,
        liked_count = this[UserTable.likedCount] ?: 0
)

fun QueryRowSet.toUserBackgroundDto() = UserBackgroundImageDto(
        background_id = this[UserBackgroundImageTable.id] ?: 0,
        background_url = this[UserBackgroundImageTable.url] ?: "",
        background_image_price = this[UserBackgroundImageTable.imagePrice] ?: 0,
        background_primary = this[UserBackgroundImageTable.primaryImage] ?: false,
        for_free = this[UserBackgroundImageTable.forFree] ?: false
)

fun QueryRowSet.toUserProfileImageDto() = UserProfileImageDto(
        profile_id = this[ProfileImageTable.id] ?: 0,
        profile_url = this[ProfileImageTable.url] ?: "",
        profile_image_price = this[ProfileImageTable.imagePrice] ?: 0,
        profile_primary = this[ProfileImageTable.primaryImage] ?: false,
        for_free = this[ProfileImageTable.forFree] ?: false
)

fun QueryRowSet.toSubscribeTypeDto() = SubscribeTypeDto(
        subscrib_id = this[SubscribeTypesTable.id] ?: 0,
        subscribe_type_name = this[SubscribeTypesTable.subscribe_name] ?: "Empty"
)

fun QueryRowSet.toUserBackgroundWithUserOwnedDto() = UserBackgroundImageWithUserOwnedDto(
        background_id = this[UserBackgroundImageTable.id] ?: 0,
        background_url = this[UserBackgroundImageTable.url] ?: "",
        background_image_price = this[UserBackgroundImageTable.imagePrice] ?: 0,
        background_primary = this[UserBackgroundImageTable.primaryImage] ?: false,
        for_free = this[UserBackgroundImageTable.forFree] ?: false,
        user_owned = this[BackgroundImageOwnedTable.backgroundImageId.isNotNull()
                .aliased("is_user_owned")] ?: false
)

fun QueryRowSet.toUserProfileImageWithUserOwnedDto() = UserProfileImageWithUserOwnedDto(
        profile_id = this[ProfileImageTable.id] ?: 0,
        profile_url = this[ProfileImageTable.url] ?: "",
        profile_image_price = this[ProfileImageTable.imagePrice] ?: 0,
        profile_primary = this[ProfileImageTable.primaryImage] ?: false,
        for_free = this[ProfileImageTable.forFree] ?: false,
        user_owned = this[UserImageProfileOwnedTable.profileImageId.isNotNull()
                .aliased("is_user_owned")] ?: false
)

fun QueryRowSet.toSmallDetailsUserDto() = SmallDetailsUserDto(
        userId = this[UserTable.userId] ?: 0,
        userName = this[UserTable.userName] ?: "0",
        userEmail = this[UserTable.email] ?: "0",
        userPassword = this[UserTable.userPassword] ?: "0",
        userSalt = this[UserTable.userSalt] ?: "0",
        pojoPrice = this[UserTable.pojo_price] ?: 0,
)