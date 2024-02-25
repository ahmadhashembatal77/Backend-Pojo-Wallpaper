package com.example.domain.usecases.collections

import com.example.data.source.dao.CollectionDao
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class UpdateUserCollectionTitleTextUseCase(
        private val collectionDao: CollectionDao
) {
    suspend operator fun invoke(title: String, collectionId: Int): BaseResponse<Boolean> {
        if (collectionId <= 0 || title == "") {
            BaseResponse.WithIssueResponse(
                    message = ResponseMessages.EmptyField.message,
                    data = false
            )
        }
        return BaseResponse.SuccessResponse(
                message = ResponseMessages.Success.message,
                data = collectionDao.updateUserCollectionTitleText(
                        collectionId = collectionId,
                        title = title
                ) == 1
        )
    }
}