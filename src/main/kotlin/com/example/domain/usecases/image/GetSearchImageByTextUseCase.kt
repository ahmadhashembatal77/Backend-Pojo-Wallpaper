package com.example.domain.usecases.image

import com.example.data.dto.imageDetails.ImageDetailsDto
import com.example.data.source.dao.ImageDao
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class GetSearchImageByTextUseCase(
    private val imageDao: ImageDao,
) {
    suspend operator fun invoke(
        imageTitle: String
    ): BaseResponse<List<ImageDetailsDto>> {

        val imageList = mutableListOf<ImageDetailsDto>()

        val images = imageDao.searchImagesByImageTitle(imageTitle = imageTitle)

        if (images.isEmpty()) {
            val imagesFromTags = imageDao.getImageSearchResultByTagName(tagName = imageTitle)
            return if (imagesFromTags.isNotEmpty()) {
                imageList.addAll(imagesFromTags)
                BaseResponse.SuccessResponse(
                        message = ResponseMessages.SuccessFetchImageDetails.message,
                        data = imageList
                )
            }else{
                BaseResponse.SuccessResponse(
                        message = ResponseMessages.EmptyFetchImages.message,
                        data = emptyList()
                )
            }
        }

        imageList.addAll(images)

        if (images.size <= 39) {
            val imagesFromTags = imageDao.getImageSearchResultByTagName(tagName = imageTitle)

            if (imagesFromTags.isNotEmpty()) {
                imageList.addAll(imagesFromTags)
            }
        }

        if(imageList.isEmpty()){
            return BaseResponse.SuccessResponse(
                    message = ResponseMessages.EmptyFetchImages.message,
                    data = emptyList()
            )
        }

        return BaseResponse.SuccessResponse(
            message = ResponseMessages.SuccessFetchImageDetails.message,
            data = imageList
        )
    }
}