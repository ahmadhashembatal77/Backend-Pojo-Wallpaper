package com.example.domain.usecases.category

import com.example.data.dto.imageDetails.ImageCategoryDto
import com.example.data.source.dao.ImageDao
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class GetAllLiteCategoriesUserCase(
    private val imageDao: ImageDao,
) {
    suspend operator fun invoke(): BaseResponse<List<ImageCategoryDto>> {

        val categories = imageDao.getAllLiteCategories()

        return if (categories.isNotEmpty()) BaseResponse.SuccessResponse(
            message = ResponseMessages.SuccessFetchImageDetails.message,
            data = categories
        )
        else BaseResponse.EmptyListResponse(message = ResponseMessages.EmptyFetchColorsImages.message)

    }
}