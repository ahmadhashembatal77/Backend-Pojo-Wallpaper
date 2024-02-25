package com.example.domain.usecases.stickers

import com.example.data.dto.stickers.StickersDetailsDto
import com.example.data.source.dao.StickersDao
import com.example.data.tables.StickersTable
import com.example.domain.usecases.util.pageNumberToCheckIfPageExist
import com.example.domain.usecases.util.pageNumberToMakeItInRange
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class GetStickersByDateUseCase(
        private val stickerDao: StickersDao
) {
    suspend operator fun invoke(pageSize: Int, pageNumber: Int, userId: Int): BaseResponse<List<StickersDetailsDto>> {
        val images = stickerDao.getStickersByDate(
                pageSize = pageSize,
                pageNumber = pageNumber,
                userId = userId
        )

        val totalPages = stickerDao.getTotalPagesTable(StickersTable.id, pageSize)

        val pageNumberInRange = pageNumber.pageNumberToMakeItInRange(totalPages = totalPages)

        if (!pageNumberInRange.pageNumberToCheckIfPageExist(totalPages = totalPages)) {
            return BaseResponse.ErrorLiseResponse(message = ResponseMessages.FailFetchImageDetails.message)
        }

        if (images.isEmpty()) {
            return BaseResponse.EmptyListResponse(message = ResponseMessages.FailFetchImageDetails.message)
        }

        return BaseResponse.SuccessResponse(
                message = ResponseMessages.SuccessFetchImageDetails.message,
                data = images
        )
    }
}