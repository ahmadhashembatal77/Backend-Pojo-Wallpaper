package com.example.domain.usecases.user

import com.example.data.dto.user.UserProfileImageDto
import com.example.data.source.dao.UserDao
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class GetOwnedUserProfileImagesUseCase (
        private val userDao: UserDao
) {
    suspend operator fun invoke(userId: Int): BaseResponse<List<UserProfileImageDto>>{
        if (userId == -1 || userId < 0)
            return BaseResponse.ErrorLiseResponse(message = ResponseMessages.EmptyFetchList.message)

        val images = userDao.getOwnedUserProfileImages(
                userId = userId
        )

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