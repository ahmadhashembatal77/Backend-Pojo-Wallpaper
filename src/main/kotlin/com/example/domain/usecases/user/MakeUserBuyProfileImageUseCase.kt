package com.example.domain.usecases.user

import com.example.data.source.dao.UserDao
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class MakeUserBuyProfileImageUseCase (
        private val userDao: UserDao
) {
    suspend operator fun invoke(
            userId: Int = -1,
            profileImageId: Int = 0,
            price : Int = 0
    ): BaseResponse<Boolean> {
        if (userId == -1 || profileImageId == 0) {
            return BaseResponse.WithIssueResponse(message = ResponseMessages.EmptyParameter.message, data = false)
        }
        val add = userDao.makeUserBuyProfileImage(
                userId = userId,
                profileImageId = profileImageId
        )
        val pojoPrice = userDao.updateUserPojoPrice(
                userId = userId,
                increase = false,
                price = price
        )
        if (add == 0 || !pojoPrice) {
            return BaseResponse.WithIssueResponse(message = ResponseMessages.UnKnowFail.message, data = false)
        }
        return BaseResponse.SuccessResponse(message = ResponseMessages.Success.message, data = true)
    }
}