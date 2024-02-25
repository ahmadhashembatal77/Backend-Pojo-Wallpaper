package com.example.domain.usecases.collections

import com.example.data.dto.imageDetails.ImageDetailsWithUserLikeAndPrimaryDto
import com.example.data.source.dao.CollectionDao
import com.example.data.source.dao.ImageDao
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class GetCollectionImagesWithImageOneImageDetailsUseCase(
        private val collectionDao: CollectionDao,
        private val imageDao: ImageDao
) {
    suspend operator fun invoke(imageId: Int, collectionId: Int, userId: Int): BaseResponse<List<ImageDetailsWithUserLikeAndPrimaryDto>> {

        if (collectionId == 0 || collectionId < 0)
            return BaseResponse.ErrorLiseResponse(message = ResponseMessages.EmptyFetchList.message)

        val imagesDetails = mutableListOf<ImageDetailsWithUserLikeAndPrimaryDto>()
        val imageDetails = imageDao.getImageDetailsBasedOnImagedId(imageId = imageId, userId = userId)

        val images = collectionDao.getImagesFromUsersCollectionsByCollectionId(
                collectionId = collectionId,
                userId = userId
        )

        if (images.isEmpty()) {
            return BaseResponse.SuccessResponse(
                    message = ResponseMessages.EmptyFetchImages.message,
                    data = emptyList()
            )
        }

        val imagesWithoutImageSelected = images.filter { it.image_id != imageId }
        imagesDetails.addAll(imagesWithoutImageSelected)
        imagesDetails.add(index = 0, element = imageDetails)

        return BaseResponse.SuccessResponse(data = imagesDetails)
    }
}