package com.example.domain.usecases.collections

import com.example.data.dto.collections.CollectionTitleImageDetailsDto
import com.example.data.source.dao.CollectionDao
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class GetALlCollectionTitleProfileImagesUseCase(
        private val collectionDao: CollectionDao
) {
    suspend operator fun invoke(userId: Int, pageSize: Int, pageNumber: Int)
    : BaseResponse<List<CollectionTitleImageDetailsDto>> {
        val images = collectionDao.getALlCollectionTitleImages(userId, pageSize = pageSize, pageNumber = pageNumber)

        if (images.isEmpty()) {
            return BaseResponse.SuccessResponse(
                    message = ResponseMessages.EmptyFetchList.message,
                    data = emptyList()
            )
        }

        return BaseResponse.SuccessResponse(
                data = images,
                message = ResponseMessages.SuccessFetchList.message
        )
    }
}