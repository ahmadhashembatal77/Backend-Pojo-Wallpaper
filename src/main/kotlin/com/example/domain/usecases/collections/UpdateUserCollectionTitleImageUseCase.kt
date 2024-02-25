package com.example.domain.usecases.collections

import com.example.data.source.dao.CollectionDao
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class UpdateUserCollectionTitleImageUseCase(
        private val collectionDao: CollectionDao
) {
    suspend operator fun invoke(collectionTitleId: Int, collectionId: Int): BaseResponse<Boolean> {
        if (collectionId <= 0 || collectionTitleId <= 0) {
            BaseResponse.WithIssueResponse(
                    message = ResponseMessages.EmptyField.message,
                    data = false
            )
        }
        return BaseResponse.SuccessResponse(
                message = ResponseMessages.Success.message,
                data = collectionDao.updateUserCollectionTitleImage(
                        collectionId = collectionId,
                        collectionTitleId = collectionTitleId
                ) == 1
        )
    }
}