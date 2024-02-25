package com.example.domain.usecases.user

import com.example.data.dto.user.UserDto
import com.example.data.source.dao.UserDao
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class GetUserDetailsByIDUserCase(
    private val userDao: UserDao,
) {
    suspend operator fun invoke(userId: Int): BaseResponse<UserDto> {

        if (userId == -1) {
            return BaseResponse.WithIssueResponse(
                message = ResponseMessages.NotFoundUserID.message,
                data = UserDto()
            )
        }

        if (!userDao.checkIfUserExistOrNotByUserId(userId = userId)) {
            return BaseResponse.WithIssueResponse(
                message = ResponseMessages.NotFoundUserID.message,
                data = UserDto()
            )
        }

        val userDetails = userDao.getUserInformationByUserId(userId = userId)

        if (userDetails.user_id == -1) {
            return BaseResponse.WithIssueResponse(
                message = ResponseMessages.NotFoundUserID.message,
                data = UserDto()
            )
        }

        return BaseResponse.SuccessResponse(
            message = ResponseMessages.SuccessFetchUserByID.message,
            data = userDetails
        )
    }
}