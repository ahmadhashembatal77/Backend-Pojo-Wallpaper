package com.example.data.dto.user


data class UserDto(
    val user_id: Int = -1,
    val user_name: String = "Empty",
    val user_email: String = "Empty",
    val subscribe_details: SubscribeTypeDto = SubscribeTypeDto(),
    val user_background: UserBackgroundImageDto = UserBackgroundImageDto(),
    val user_profile: UserProfileImageDto = UserProfileImageDto(),
    val friend_count: Int = 0,
    val pojo_price: Int = 50,
    val liked_count: Int = 0
)

data class SubscribeTypeDto(
    val subscrib_id: Int = 1,
    val subscribe_type_name: String = "Empty"
)

data class UserBackgroundImageDto(
    val background_id: Int = 1,
    val background_url: String = "",
    val background_image_price: Int = 1,
    val background_primary: Boolean = false,
    val for_free: Boolean = false
)

data class UserProfileImageDto(
    val profile_id: Int = 1,
    val profile_url: String = "",
    val profile_image_price: Int = 1,
    val profile_primary: Boolean = false,
    val for_free: Boolean = false,
)

data class UserBackgroundImageWithUserOwnedDto(
    val background_id: Int = 1,
    val background_url: String = "",
    val background_image_price: Int = 1,
    val background_primary: Boolean = false,
    val for_free: Boolean = false,
    val user_owned: Boolean = false
)

data class UserProfileImageWithUserOwnedDto(
    val profile_id: Int = 1,
    val profile_url: String = "",
    val profile_image_price: Int = 1,
    val profile_primary: Boolean = false,
    val for_free: Boolean = false,
    val user_owned: Boolean = false
)

data class SmallDetailsUserDto(
    val userId: Int = -1,
    val userName: String = "",
    val userEmail: String = "",
    val userPassword: String = "",
    val userSalt: String = "",
    val pojoPrice: Int = 0
)