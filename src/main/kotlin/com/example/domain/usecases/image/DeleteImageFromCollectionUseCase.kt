package com.example.domain.usecases.image

import com.example.data.source.dao.CollectionDao
import com.example.data.source.dao.ImageDao
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class DeleteImageFromCollectionUseCase(
    private val imageDao: ImageDao,
    private val collectionDao: CollectionDao
) {
    suspend operator fun invoke(collectionId: Int = 0, imageId: Int = 0): BaseResponse<Boolean> {
        if (collectionId == 0 || imageId == 0) {
            return BaseResponse.WithIssueResponse(message = ResponseMessages.EmptyParameter.message, data = false)
        }

        val removeLiked = imageDao.deleteImageFromUserCollection(collectionId = collectionId, imageId = imageId)

        if (!removeLiked) {
            return BaseResponse.WithIssueResponse(message = ResponseMessages.UnKnowFail.message, data = false)
        }

        if (collectionDao.updateImageNumberToUserCollection(collectionId = collectionId, isIncrease = false) == 0) {
            return BaseResponse.WithIssueResponse(message = ResponseMessages.UnKnowFail.message, data = false)
        }
        return BaseResponse.SuccessResponse(message = ResponseMessages.SuccessRemoveLikeImage.message, data = true)
    }
}