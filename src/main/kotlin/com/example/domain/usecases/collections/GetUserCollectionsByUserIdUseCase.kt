package com.example.domain.usecases.collections

import com.example.data.dto.collections.CollectionWithUserDto
import com.example.data.source.dao.CollectionDao
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class GetUserCollectionsByUserIdUseCase(
        private val collectionDao: CollectionDao
) {
    suspend operator fun invoke(userId: Int = -1): BaseResponse<List<CollectionWithUserDto>> {

        if (userId == -1 || userId < 0)
            return BaseResponse.ErrorLiseResponse(message = ResponseMessages.WrongUserId.message)

        val collections = collectionDao.getAllUserCollectionsByUserId(
                userId = userId
        )

        if (collections.isEmpty()) {
            return BaseResponse.SuccessResponse(
                    message = ResponseMessages.EmptyFetchCollections.message,
                    data = emptyList()
            )
        }

        return BaseResponse.SuccessResponse(
                data = collections,
                message = ResponseMessages.SuccessFetchList.message
        )
    }
}