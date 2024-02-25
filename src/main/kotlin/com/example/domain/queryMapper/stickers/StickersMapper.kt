package com.example.domain.queryMapper.stickers

import com.example.data.dto.stickers.StickerCollectionDto
import com.example.data.dto.stickers.StickersDetailsDto
import com.example.data.tables.StickersCollectionsTable
import com.example.data.tables.StickersTable
import com.example.data.tables.UserStickersOwnedTable
import org.ktorm.dsl.QueryRowSet
import org.ktorm.dsl.isNotNull

fun QueryRowSet.toStickersRowMapper() = StickersDetailsDto(
        sticker_id = this[StickersTable.id] ?: 0,
        sticker_url = this[StickersTable.url] ?: "Empty",
        sticker_title = this[StickersTable.title] ?: "Empty",
        watch_count = this[StickersTable.watchCount] ?: 0,
        sticker_price = this[StickersTable.price] ?: 0,
        sticker_primary = this[StickersTable.primary] ?: false,
        first_like_count = this[StickersTable.firstLikeCount] ?: 0,
        second_like_count = this[StickersTable.secondLikeCount] ?: 0,
        third_like_count = this[StickersTable.thirdLikeCount] ?: 0,
        fourth_like_count = this[StickersTable.fourthLikeCount] ?: 0,
        fifth_like_count = this[StickersTable.fifthLikeCount] ?: 0,
        is_user_owned_sticker = this[UserStickersOwnedTable.stickerId.isNotNull()
                .aliased("is_user_owned_sticker")] ?: false
)

fun QueryRowSet.toStickerCollectionRowMapper() = StickerCollectionDto(
        sticker_collection_id = this[StickersCollectionsTable.id]?:0,
        sticker_collection_url = this[StickersCollectionsTable.url]?:"0",
        sticker_collection_name = this[StickersCollectionsTable.name]?:""
)