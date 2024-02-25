package com.example.data.dto.imageDetails


data class ImageDetailsWithUserLikeAndPrimaryDto(
    val image_id: Int,
    val image_url: String,
    val blur_hash: String,
    val image_title: String,
    val like_count: Int,
    val watch_count: Int,
    val image_price: Int,
    val image_primary: Boolean,
    val user_liked: Boolean,
    val user_owned_primary: Boolean,
    val category_id: Int,
)

data class IdAndUrlImagesWithDto(
    val image_id: Int,
    val image_url: String,
    val blur_hash: String,
)

data class ImageDetailsDto(
    val image_id: Int,
    val image_url: String,
    val blur_hash: String,
    val image_title: String,
    val like_count: Int,
    val watch_count: Int,
    val image_price: Int,
    val image_primary: Boolean,
    val category_id: Int
)