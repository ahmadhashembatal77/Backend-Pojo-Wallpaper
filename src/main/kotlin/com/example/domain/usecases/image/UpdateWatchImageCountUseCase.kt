package com.example.domain.usecases.image

import com.example.data.source.dao.ImageDao
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class UpdateWatchImageCountUseCase(
    private val imageDao: ImageDao
) {
    suspend operator fun invoke(imageId: Int = 0): BaseResponse<Boolean> {
        if (imageId == 0) {
            return BaseResponse.WithIssueResponse(message = ResponseMessages.EmptyParameter.message, data = false)
        }
        val updateWatch = imageDao.updateImageWatchCount(imageId = imageId)

        if (!updateWatch) {
            return BaseResponse.WithIssueResponse(message = ResponseMessages.UnKnowFail.message, data = false)
        }
        return BaseResponse.SuccessResponse(
            message = ResponseMessages.SuccessUpdateWatchImageCount.message,
            data = true
        )
    }
}