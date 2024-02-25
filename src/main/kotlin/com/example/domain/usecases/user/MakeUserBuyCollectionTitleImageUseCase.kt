package com.example.domain.usecases.user

import com.example.data.source.dao.UserDao
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class MakeUserBuyCollectionTitleImageUseCase(
        private val userDao: UserDao
) {
    suspend operator fun invoke(
            userId: Int = -1,
            collectionTitleImageId: Int = 0,
            price : Int = 0
    ): BaseResponse<Boolean> {
        if (userId == -1 || collectionTitleImageId == 0) {
            return BaseResponse.WithIssueResponse(message = ResponseMessages.EmptyParameter.message, data = false)
        }
        val addLiked = userDao.makeUserBuyCollectionTitleImage(
                userId = userId,
                collectionTitleId = collectionTitleImageId
        )
        val pojoPrice = userDao.updateUserPojoPrice(
                userId = userId,
                increase = false,
                price = price
        )
        if (addLiked == 0 || !pojoPrice) {
            return BaseResponse.WithIssueResponse(message = ResponseMessages.UnKnowFail.message, data = false)
        }
        return BaseResponse.SuccessResponse(message = ResponseMessages.Success.message, data = true)
    }
}