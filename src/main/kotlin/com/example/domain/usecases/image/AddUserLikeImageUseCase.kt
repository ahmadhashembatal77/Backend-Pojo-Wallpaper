package com.example.domain.usecases.image

import com.example.data.source.dao.ImageDao
import com.example.utils.BaseResponse
import com.example.utils.BaseResponse.WithIssueResponse
import com.example.utils.ResponseMessages
class AddUserLikeImageUseCase(
    private val imageDao: ImageDao
) {
    suspend operator fun invoke(userId: Int = -1, imageId: Int = 0): BaseResponse<Boolean> {
        if (userId == -1 || imageId == 0) {
            return WithIssueResponse(message = ResponseMessages.EmptyParameter.message, data = false)
        }
        val addLiked = imageDao.addUserLikeImageUseCase(userId = userId, imageId = imageId)
        if (!addLiked) {
            return WithIssueResponse(message = ResponseMessages.UnKnowFail.message, data = false)
        }
        return BaseResponse.SuccessResponse(message = ResponseMessages.SuccessAddLikeImage.message, data = true)
    }
}