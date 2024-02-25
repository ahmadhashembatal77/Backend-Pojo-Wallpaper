package com.example.data.source.dao

import com.example.data.dto.stickers.StickerCollectionDto
import com.example.data.dto.stickers.StickersDetailsDto
import com.example.data.source.queries.*
import com.example.domain.queryMapper.stickers.toStickerCollectionRowMapper
import com.example.domain.queryMapper.stickers.toStickersRowMapper
import org.ktorm.database.Database
import org.ktorm.dsl.map
import org.ktorm.schema.Column

class StickersDaoImpl(
        private var dataBase: Database,
) : StickersDao {
    override suspend fun getStickersByDate(pageSize: Int, pageNumber: Int, userId: Int): List<StickersDetailsDto> {
        return dataBase.getStickersByStickerDateQuery(pageSize = pageSize,
                pageNumber = pageNumber,
                userId = userId
        ).map { it.toStickersRowMapper() }
    }

    override suspend fun getTotalPagesTable(columnName: Column<Int>, pageSize: Int): Int {
        return dataBase.getTotalPagesTableQuery(columnName, pageSize)
    }

    override suspend fun updateStickerLikeCount(likeType: StickerLikeType, stickerId: Int): Int {
        return dataBase.updateStickerLikeCountBasedOneLikeTypeQuery(
                likeType = likeType,
                stickerId = stickerId
        )
    }

    override suspend fun getLimitAllStickerCollections(collectionLimit: Int): List<StickerCollectionDto> {
        return dataBase.getLimitAllStickerCollectionsQuery(collectionLimit = collectionLimit).map {
            it.toStickerCollectionRowMapper()
        }
    }

    override suspend fun getStickersFromCollection(collectionId: Int, userId: Int): List<StickersDetailsDto> {
        return dataBase.getStickersFromCollectionQuery(collectionId = collectionId, userId = userId).map {
            it.toStickersRowMapper()
        }
    }

    override suspend fun insertStickerToUserStickerOwned(stickerId: Int, userId: Int): Boolean {
        return dataBase.insertStickerToUserStickerOwnedQuery(stickerId = stickerId, userId = userId) != 0
    }


    override suspend fun getLimitAllOwnedUserStickers(userId: Int, limit: Int): List<StickersDetailsDto> {
        return dataBase.getLimitAllOwnedUserStickersQuery(userId = userId, limit = limit).map {
            it.toStickersRowMapper()
        }
    }

    override suspend fun insertStickerImage(url: String, codeName: String, id: Int) {
        dataBase.insertStickerImageQuery(url = url, codeName = codeName, id = id)
    }
}