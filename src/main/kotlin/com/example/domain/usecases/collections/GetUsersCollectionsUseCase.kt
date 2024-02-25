package com.example.domain.usecases.collections

import com.example.data.dto.collections.CollectionWithUserDto
import com.example.data.source.dao.CollectionDao
import com.example.data.tables.UserCollectionTable
import com.example.domain.usecases.util.makePageNumberDefaultIfItZero
import com.example.domain.usecases.util.makePageSizeInRange
import com.example.domain.usecases.util.pageNumberToCheckIfPageExist
import com.example.domain.usecases.util.pageNumberToMakeItInRange
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class GetUsersCollectionsUseCase(
    private val collectionDao: CollectionDao,
) {
    suspend operator fun invoke(
        page: Int = 1,
        pageSize: Int = 10,
        userId: Int = 1,
        invisibility: Boolean = false,
        minimumNumberOfImages: Int = 50,
        maximumNumberOfImages: Int = 60
    ): BaseResponse<List<CollectionWithUserDto>> {

        val totalPageNumber = collectionDao.getTotalPagesTable(UserCollectionTable.id, pageSize)

        val pageNumberInRange = page.pageNumberToMakeItInRange(
            totalPages = totalPageNumber
        )

        if (!pageNumberInRange.pageNumberToCheckIfPageExist(totalPages = totalPageNumber)) {
            return BaseResponse.ErrorLiseResponse(message = ResponseMessages.FailFetchMorePages.message)
        }

        val collections = collectionDao.getUsersCollections(
            pageSize = pageSize.makePageSizeInRange(),
            page = pageNumberInRange.makePageNumberDefaultIfItZero(),
            userId = userId,
            invisibility = invisibility,
            minimumNumberOfImages = minimumNumberOfImages,
            maximumNumberOfImages = maximumNumberOfImages
        )

        if (collections.isEmpty()) {
            return BaseResponse.SuccessResponse(
                message = ResponseMessages.EmptyFetchCollections.message,
                data = emptyList()
            )
        }

        return BaseResponse.SuccessResponse(
            data = collections,
            message = ResponseMessages.SuccessFetchList.message
        )
    }
}