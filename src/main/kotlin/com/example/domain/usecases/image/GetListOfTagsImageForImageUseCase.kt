package com.example.domain.usecases.image

import com.example.data.dto.tags.TagsDto
import com.example.data.source.dao.ImageDao
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class GetListOfTagsImageForImageUseCase(
        private val imageDao: ImageDao
) {
    suspend operator fun invoke(imageId: Int): BaseResponse<List<TagsDto>>{
        val tags = imageDao.getListOfImageTagsDependOnImageId(imageId = imageId)

        return if (tags.isNotEmpty())
            BaseResponse.SuccessResponse(
                    message = ResponseMessages.SuccessFetchImageTags.message,
                    data = tags
            )
        else BaseResponse.EmptyListResponse(
                message = ResponseMessages.EmptyFetchTags.message
        )
    }
}