package com.example.domain.usecases.user

import com.example.data.source.dao.UserDao
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class UpdateUserImageProfileUseCase(
        private val userDao: UserDao
) {
    suspend operator fun invoke(userId: Int, profileImageId: Int): BaseResponse<Boolean> {
        if(userId <= -1){
            return BaseResponse.WithIssueResponse(
                    message = ResponseMessages.EmptyField.message,
                    data = false
            )
        }
        return BaseResponse.SuccessResponse(
                message = ResponseMessages.Success.message,
                data = userDao.updateUserImageProfile(userId = userId, profileImageId = profileImageId) == 1
        )
    }
}