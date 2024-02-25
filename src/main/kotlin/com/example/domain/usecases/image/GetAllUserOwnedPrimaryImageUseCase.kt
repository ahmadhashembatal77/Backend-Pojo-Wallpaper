package com.example.domain.usecases.image

import com.example.data.dto.imageDetails.ImageDetailsWithUserLikeAndPrimaryDto
import com.example.data.source.dao.ImageDao
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class GetAllUserOwnedPrimaryImageUseCase(
        private val imageDao: ImageDao
) {
    suspend operator fun invoke(
            pageSize: Int,
            pageNumber: Int,
            userId: Int
    ): BaseResponse<List<ImageDetailsWithUserLikeAndPrimaryDto>> {
        val images = imageDao.getAllUserOwnedPrimaryImage(pageSize = pageSize, pageNumber = pageNumber, userId = userId)

//        val totalPages = imageDao.getTotalPagesTable(ImageDetailsTable.id, pageSize)

//        val pageNumberInRange = pageNumber.pageNumberToMakeItInRange(totalPages = totalPages)
//
//        if (!pageNumberInRange.pageNumberToCheckIfPageExist(totalPages = totalPages)) {
//            return BaseResponse.ErrorLiseResponse(message = ResponseMessages.FailFetchImageDetails.message)
//        }

        if (images.isEmpty()) {
            return BaseResponse.SuccessResponse(
                    message = ResponseMessages.SuccessFetchImageDetails.message,
                    data = emptyList()
            )
        }
        return BaseResponse.SuccessResponse(
                message = ResponseMessages.SuccessFetchImageDetails.message,
                data = images
        )
    }
}