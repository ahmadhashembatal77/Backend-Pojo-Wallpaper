package com.example.domain.usecases.stickers

import com.example.data.source.dao.StickersDao
import com.example.data.source.dao.UserDao
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class InsertStickerToUserStickerOwnedUseCase(
        private val stickerDao: StickersDao,
        private val userDao: UserDao
) {
    suspend operator fun invoke(
            stickerId: Int,
            userId: Int,
            price : Int
    ): BaseResponse<Boolean> {
        val insert = stickerDao.insertStickerToUserStickerOwned(
                stickerId = stickerId,
                userId = userId
        )
        val pojoPrice = userDao.updateUserPojoPrice(
                userId = userId,
                increase = false,
                price = price
        )
        if (!insert || !pojoPrice) {
            return BaseResponse.WithIssueResponse(
                    message = ResponseMessages.FailInsertStickerToOwned.message,
                    data = false
            )
        }
        return BaseResponse.SuccessResponse(
                message = ResponseMessages.SuccessInsertStickerToOwned.message,
                data = true
        )
    }
}