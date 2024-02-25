package com.example.domain.usecases.user

import com.example.data.dto.user.UserProfileImageWithUserOwnedDto
import com.example.data.source.dao.UserDao
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class GetAllUsersProfileImagesUseCase(
        private val userDao: UserDao
) {
    suspend operator fun invoke(userId: Int, pageSize: Int, pageNumber: Int)
            : BaseResponse<List<UserProfileImageWithUserOwnedDto>> {
        if (userId == -1 || userId < 0)
            return BaseResponse.ErrorLiseResponse(message = ResponseMessages.EmptyFetchList.message)

        val images = userDao.getAllUsersProfileImages(userId = userId, pageSize = pageSize, pageNumber = pageNumber)

        if (images.isEmpty()) {
            return BaseResponse.SuccessResponse(
                    message = ResponseMessages.EmptyFetchList.message,
                    data = emptyList()
            )
        }

        return BaseResponse.SuccessResponse(
                data = images,
                message = ResponseMessages.SuccessFetchList.message
        )
    }
}