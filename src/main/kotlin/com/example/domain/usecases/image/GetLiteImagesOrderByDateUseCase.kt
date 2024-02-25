package com.example.domain.usecases.image

import com.example.data.dto.imageDetails.ImageDetailsDto
import com.example.data.source.dao.ImageDao
import com.example.domain.usecases.util.makePageSizeInRange
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class GetLiteImagesOrderByDateUseCase(
    private val imageDao: ImageDao,
) {
    suspend operator fun invoke(pageSize: Int = 10, pageNumber: Int = 1)
    : BaseResponse<List<ImageDetailsDto>>
    {
//        val totalPageNumber = imageDao.getTotalPagesTable(ImageDetailsTable.id, pageSize)
//
//        val pageNumberInRange = pageNumber.pageNumberToMakeItInRange(
//            totalPages = totalPageNumber
//        )
//
//        if (!pageNumberInRange.pageNumberToCheckIfPageExist(totalPages = totalPageNumber)) {
//            return BaseResponse.ErrorLiseResponse(message = ResponseMessages.FailFetchMorePages.message)
//        }

        val images = imageDao.getLiteImagesByDate(
            pageSize.makePageSizeInRange(), pageNumber
        )

        if(images.isEmpty()){
            return BaseResponse.EmptyListResponse(message = ResponseMessages.EmptyFetchImages.message)
        }

        return BaseResponse.SuccessResponse(
            message = ResponseMessages.SuccessFetchImageDetails.message,
            data = images
        )
    }
}