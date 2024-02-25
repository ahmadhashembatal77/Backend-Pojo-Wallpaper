package com.example.domain.usecases.image

import com.example.data.dto.imageDetails.ImageDetailsDto
import com.example.data.source.dao.ImageDao
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class GetTopRatedLiteImagesThreeWeeksAgoUseCase(
    private val imagesDao: ImageDao,
) {
    suspend operator fun invoke(limit: Int): BaseResponse<List<ImageDetailsDto>> {

        val theList = imagesDao.getTenTopRatedLiteImagesThreeWeeksAgo(limit = limit)

        return if (theList.isNotEmpty())
            BaseResponse.SuccessResponse(
                message = ResponseMessages.SuccessFetchImageDetails.message,
                data = theList
            )

        else BaseResponse.EmptyListResponse(
            message = ResponseMessages.SuccessFetchImageDetails.message,
                data = emptyList()
        )
    }
}