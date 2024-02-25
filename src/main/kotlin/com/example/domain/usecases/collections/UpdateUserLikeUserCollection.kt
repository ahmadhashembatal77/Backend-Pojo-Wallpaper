package com.example.domain.usecases.collections

import com.example.data.source.dao.CollectionDao
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class UpdateUserLikeUserCollection(private val collectionDao: CollectionDao) {
    suspend operator fun invoke(userId: Int = -1, collectionId: Int = 0): BaseResponse<Boolean> {

        if (userId == -1 || collectionId == 0) {
            return BaseResponse.WithIssueResponse(message = ResponseMessages.EmptyParameter.message, data = false)
        }

        val checkIfLiked = collectionDao.checkIfUserLikedUserCollection(
                userId = userId,
                collectionId = collectionId
        )

        return if (checkIfLiked) {
            val removeLiked = collectionDao.removeUserLikeCollection(userId = userId, collectionId = collectionId)
            val updateCollectionLiked = collectionDao.updateLikedUserCollectionCountByDecrease(
                    collectionId = collectionId
            )
            if (!removeLiked || !updateCollectionLiked) {
                BaseResponse.WithIssueResponse(message = ResponseMessages.UnKnowFail.message, data = false)
            }
            BaseResponse.SuccessResponse(
                    message = ResponseMessages.SuccessRemoveLikeUserCollection.message,
                    data = true
            )
        } else {
            val addLiked = collectionDao.addUserLikeToCollection(userId = userId, collectionId = collectionId)
            val updateCollectionLiked = collectionDao.updateLikedUserCollectionCountByIncrease(
                    collectionId = collectionId
            )
            if (!addLiked || !updateCollectionLiked) {
                return BaseResponse.WithIssueResponse(message = ResponseMessages.UnKnowFail.message, data = false)
            }
            return BaseResponse.SuccessResponse(
                    message = ResponseMessages.SuccessAddLikeUserCollection.message,
                    data = true
            )
        }
    }
}