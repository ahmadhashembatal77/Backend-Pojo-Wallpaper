package com.example.domain.usecases.image

import com.example.data.source.dao.ImageDao
import com.example.data.source.dao.UserDao
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class AddPrimaryImageToUserOwnedUserCase (
        private val imageDao: ImageDao,
        private val userDao: UserDao
) {
    suspend operator fun invoke(userId: Int = -1, imageId: Int = 0, price: Int): BaseResponse<Boolean> {
        if (userId <= -1 || imageId == 0) {
            return BaseResponse.WithIssueResponse(message = ResponseMessages.EmptyParameter.message, data = false)
        }
        val addLiked = imageDao.addPrimaryImageToUser(userId = userId, imageId = imageId)
        if (!addLiked) {
            return BaseResponse.WithIssueResponse(message = ResponseMessages.UnKnowFail.message, data = false)
        }
        userDao.updateUserPojoPrice(
                userId = userId,
                increase = false,
                price = price
        )
        return BaseResponse.SuccessResponse(message = ResponseMessages.SuccessAddLikeImage.message, data = true)
    }
}