package com.example.data.dto.stickers


data class StickersDetailsDto(
    val sticker_id: Int,
    val sticker_url: String,
    val sticker_title: String,
    val watch_count: Int,
    val sticker_price: Int,
    val sticker_primary: Boolean,
    val first_like_count: Int,
    val second_like_count: Int,
    val third_like_count: Int,
    val fourth_like_count: Int,
    val fifth_like_count: Int,
    val is_user_owned_sticker: Boolean
)

data class StickerCollectionDto(
    val sticker_collection_id: Int,
    val sticker_collection_name: String,
    val sticker_collection_url: String
)