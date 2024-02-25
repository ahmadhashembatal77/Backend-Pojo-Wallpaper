package com.example.domain.usecases.image

import com.example.data.dto.imageDetails.ImageDetailsWithUserLikeAndPrimaryDto
import com.example.data.source.dao.ImageDao
import com.example.domain.usecases.util.makePageSizeInRange
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class GetAllLiteImagesByCategory(
        private val imageDao: ImageDao,
) {
    suspend operator fun invoke(
            pageSize: Int,
            page: Int,
            categoryId: Int,
            categoryName: String
    ): BaseResponse<List<ImageDetailsWithUserLikeAndPrimaryDto>> {
        val images = imageDao.getAllLiteImageByCategories(
                pageSize = pageSize.makePageSizeInRange(),
                page = page,
                categoryId = categoryId
        )

        if (images.isEmpty()) {
            return BaseResponse.SuccessResponse(
                    message = ResponseMessages.EmptyFetchImages.message,
                    data = emptyList()
            )
        }

        return BaseResponse.SuccessResponse(
                message = ResponseMessages.SuccessFetchImageDetails.message,
                data = images
        )
    }
}