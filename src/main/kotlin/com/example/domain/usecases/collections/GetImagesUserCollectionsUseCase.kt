package com.example.domain.usecases.collections

import com.example.data.dto.imageDetails.ImageDetailsDto
import com.example.data.source.dao.CollectionDao
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class GetImagesUserCollectionsUseCase(
    private val collectionDao: CollectionDao,
) {
    suspend operator fun invoke(collectionId: Int): BaseResponse<List<ImageDetailsDto>> {

        if (collectionId == 0 || collectionId < 0)
            return BaseResponse.ErrorLiseResponse(message = ResponseMessages.EmptyFetchList.message)

        val images = collectionDao.getImagesFromUsersCollectionsByCollectionIdWithoutUserDetails(
            collectionId = collectionId
        )

        if (images.isEmpty()) {
            return BaseResponse.SuccessResponse(
                    message = ResponseMessages.EmptyFetchImages.message,
                    data = emptyList()
            )
        }

        return BaseResponse.SuccessResponse(
            data = images,
            message = ResponseMessages.SuccessFetchList.message
        )
    }
}