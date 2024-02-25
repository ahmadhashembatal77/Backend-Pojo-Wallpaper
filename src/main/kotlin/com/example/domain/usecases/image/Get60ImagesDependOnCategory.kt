package com.example.domain.usecases.image

import com.example.data.dto.imageDetails.ImageDetailsWithUserLikeAndPrimaryDto
import com.example.data.source.dao.ImageDao
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class Get60ImagesDependOnCategory(
    private val imageDao: ImageDao,
) {
    suspend operator fun invoke(
        imageId: Int,
        categoryId: Int,
        userId: Int,
    ): BaseResponse<List<ImageDetailsWithUserLikeAndPrimaryDto>> {

        val imagesDetails = mutableListOf<ImageDetailsWithUserLikeAndPrimaryDto>()

        val imageDetails = imageDao.getImageDetailsBasedOnImagedId(imageId = imageId, userId = userId)

        if (imageDetails.image_id == 0) {
            return BaseResponse.ErrorLiseResponse(message = ResponseMessages.NotFoundImageByImageID.message)
        }

        val images = imageDao.getImagesDetailsBasedOnCategoryORColorId(
            categoryId = categoryId,
            userId = userId
        ).filter { it.image_id != imageId }.shuffled()

        if (images.isEmpty()) {
            return BaseResponse.EmptyListResponse(message = ResponseMessages.EmptyFetchImages.message)
        }

        imagesDetails.addAll(images)

        imagesDetails.shuffled()

        if (images.size < 50) {
            val imageDetailsRandom = imageDao.getImagesDetailsBasedOnRandomCategoryID(
                limit = 60 - images.size - 1,
                userId = userId
            )
            return if (imageDetailsRandom.isEmpty()) {
                BaseResponse.EmptyListResponse(message = ResponseMessages.EmptyFetchImages.message)
            } else {
                imagesDetails.addAll(imageDetailsRandom)
                imagesDetails.add(0, imageDetails)
                BaseResponse.SuccessResponse(
                    data = imagesDetails
                )
            }
        }

        imagesDetails.add(0, imageDetails)

        return BaseResponse.SuccessResponse(
            data = imagesDetails
        )
    }
}