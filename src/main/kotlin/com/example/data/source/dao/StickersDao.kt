package com.example.data.source.dao

import com.example.data.dto.stickers.StickerCollectionDto
import com.example.data.dto.stickers.StickersDetailsDto
import com.example.data.source.queries.StickerLikeType
import org.ktorm.schema.Column

interface StickersDao {
    suspend fun getStickersByDate(pageSize: Int, pageNumber: Int,userId: Int): List<StickersDetailsDto>
    suspend fun getTotalPagesTable(columnName: Column<Int>, pageSize: Int): Int
    suspend fun updateStickerLikeCount(likeType: StickerLikeType, stickerId: Int): Int
    suspend fun getLimitAllStickerCollections(collectionLimit: Int): List<StickerCollectionDto>
    suspend fun getStickersFromCollection(collectionId: Int, userId: Int): List<StickersDetailsDto>
    suspend fun insertStickerToUserStickerOwned(stickerId: Int, userId: Int): Boolean
    suspend fun getLimitAllOwnedUserStickers(userId: Int, limit: Int): List<StickersDetailsDto>

    /**
    Developer Mood
     */
    suspend fun insertStickerImage(url: String, codeName: String, id: Int)
}