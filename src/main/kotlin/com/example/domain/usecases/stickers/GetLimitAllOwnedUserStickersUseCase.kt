package com.example.domain.usecases.stickers

import com.example.data.dto.stickers.StickersDetailsDto
import com.example.data.source.dao.StickersDao
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class GetLimitAllOwnedUserStickersUseCase(private val stickerDao: StickersDao) {
    suspend operator fun invoke(userId: Int, limit: Int): BaseResponse<List<StickersDetailsDto>> {
        val stickers = stickerDao.getLimitAllOwnedUserStickers(
                userId = userId,
                limit = limit
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