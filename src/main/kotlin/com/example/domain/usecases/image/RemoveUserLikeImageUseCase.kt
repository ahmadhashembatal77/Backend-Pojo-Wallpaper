package com.example.domain.usecases.image

import com.example.data.source.dao.ImageDao
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class RemoveUserLikeImageUseCase (
    private val imageDao: ImageDao
) {
    suspend operator fun invoke(userId: Int = 0, imageId: Int = 0): BaseResponse<Boolean> {
        if (userId == -1 || imageId == 0) {
            return BaseResponse.WithIssueResponse(message = ResponseMessages.EmptyParameter.message, data = false)
        }
        val removeLiked = imageDao.removeUserLikeImageUseCase(userId = userId, imageId = imageId)
        if (!removeLiked) {
            return BaseResponse.WithIssueResponse(message = ResponseMessages.UnKnowFail.message, data = false)
        }
        return BaseResponse.SuccessResponse(message = ResponseMessages.SuccessRemoveLikeImage.message, data = true)
    }
}