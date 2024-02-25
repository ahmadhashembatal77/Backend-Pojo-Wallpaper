package com.example.domain.usecases.stickers

import com.example.data.dto.stickers.StickersDetailsDto
import com.example.data.source.dao.StickersDao
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class GetStickersFromCollectionUseCase(private val stickerDao: StickersDao) {
    suspend operator fun invoke(collectionId: Int, userId: Int): BaseResponse<List<StickersDetailsDto>> {
        val stickers = stickerDao.getStickersFromCollection(
                collectionId = collectionId,
                userId = userId
        )

        if (stickers.isEmpty()) {
            return BaseResponse.WithIssueResponse(
                    message = ResponseMessages.FailGetStickers.message,
                    data = emptyList()
            )
        }

        return BaseResponse.SuccessResponse(
                message = ResponseMessages.SuccessGetStickers.message,
                data = stickers
        )
    }
}