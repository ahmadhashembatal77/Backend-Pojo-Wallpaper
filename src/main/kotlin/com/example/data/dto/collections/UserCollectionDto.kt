package com.example.data.dto.collections


data class CollectionWithUserDto(
    val collection_id: Int,
    val collection_name: String,
    val is_collection_visible: Boolean,
    val likesCount: Int,
    val collection_url: CollectionTitleImageDetailsDto,
    val user_id: Int,
    val user_name: String,
    val user_url: String,
    val user_liked: Boolean,
    val image_number: Int,
    val user_owned: Boolean,
)

data class CollectionTitleImageDetailsDto(
    val collection_title_id: Int = 0,
    val collection_title_url: String = "",
    val collection_title_primary_image: Boolean = false,
    val collection_title_image_price: Int = 0,
    val is_user_owned: Boolean = false,
)

data class CollectionDetailsForCreateNewOne(
    val user_id: Int,
    val collection_name: String,
    val collection_invisibility: Boolean,
)