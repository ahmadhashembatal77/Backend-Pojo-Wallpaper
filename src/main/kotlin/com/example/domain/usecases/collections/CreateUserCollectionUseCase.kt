package com.example.domain.usecases.collections

import com.example.data.dto.collections.CollectionDetailsForCreateNewOne
import com.example.data.source.dao.CollectionDao
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class CreateUserCollectionUseCase(
        private val collectionDao: CollectionDao
) {
    suspend operator fun invoke(collection: CollectionDetailsForCreateNewOne): BaseResponse<Boolean> {
        if (collection.user_id <= -1 || collection.collection_name == "") {
            return BaseResponse.WithIssueResponse(
                    message = ResponseMessages.EmptyField.message,
                    data = false
            )
        }

        return BaseResponse.SuccessResponse(
                message = ResponseMessages.Success.message,
                data = collectionDao.createUserCollection(collection) == 1
        )
    }
}