package com.example.domain.usecases.image

import com.example.data.dto.imageDetails.ImageDetailsDto
import com.example.data.source.dao.ImageDao
import com.example.domain.usecases.util.makePageSizeInRange
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class GetListOfTopRatedLiteImages(
        private val imageDao: ImageDao,
) {
    suspend operator fun invoke(pageSize: Int, pageNumber: Int)
            : BaseResponse<List<ImageDetailsDto>> {

//        val totalPageNumber = imageDao.getTotalPagesTable(ImageDetailsTable.id, pageSize)
//
//        val pageNumberInRange = pageNumber.pageNumberToMakeItInRange(
//                totalPages = totalPageNumber
//        )
//
//        if (!pageNumberInRange.pageNumberToCheckIfPageExist(totalPages = totalPageNumber)) {
//            return BaseResponse.ErrorLiseResponse(message = ResponseMessages.FailFetchImageDetails.message)
//        }

        val topRatedImages = imageDao.getTopRatedLiteImages(
                pageSize = pageSize.makePageSizeInRange(),
                pageNumber = pageNumber
        )

        if (topRatedImages.isEmpty()) {
            return BaseResponse.EmptyListResponse(message = ResponseMessages.EmptyFetchImages.message)
        }

        return BaseResponse.SuccessResponse(
                message = ResponseMessages.SuccessFetchImageDetails.message,
                data = topRatedImages
        )
    }
}