package com.example.domain.usecases.collections

import com.example.data.dto.collections.CollectionWithUserDto
import com.example.data.source.dao.CollectionDao
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class GetUserCollectionDetailsUseCase(
        private val collectionDao: CollectionDao,
) {
    suspend operator fun invoke(collectionId: Int, userId: Int): BaseResponse<CollectionWithUserDto> {

        val collection: CollectionWithUserDto = collectionDao.getUserCollectionDetails(
                collectionId = collectionId, userId = userId
        )

        if (collection.collection_id == 0) {
            return BaseResponse.ErrorLiseResponse(message = ResponseMessages.EmptyFetchData.message)
        }

        return BaseResponse.SuccessResponse(
                data = collection,
                message = ResponseMessages.SuccessFetchList.message
        )
    }
}