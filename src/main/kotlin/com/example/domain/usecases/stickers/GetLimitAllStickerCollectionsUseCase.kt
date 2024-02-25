package com.example.domain.usecases.stickers

import com.example.data.dto.stickers.StickerCollectionDto
import com.example.data.source.dao.StickersDao
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class GetLimitAllStickerCollectionsUseCase(private val stickerDao: StickersDao) {
    suspend operator fun invoke(collectionLimit: Int): BaseResponse<List<StickerCollectionDto>> {
        val stickersCollections = stickerDao.getLimitAllStickerCollections(
                collectionLimit = collectionLimit
        )

        if (stickersCollections.isEmpty()) {
            return BaseResponse.WithIssueResponse(
                    message = ResponseMessages.FailGetStickers.message,
                    data = emptyList()
            )
        }

        return BaseResponse.SuccessResponse(
                message = ResponseMessages.SuccessGetStickers.message,
                data = stickersCollections
        )
    }
}