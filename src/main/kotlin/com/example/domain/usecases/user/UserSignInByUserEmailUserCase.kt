package com.example.domain.usecases.user

import com.example.data.response.UserResponseWithToken
import com.example.data.source.dao.UserDao
import com.example.domain.SaltedHash
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages
import com.example.utils.generateToken
import com.example.utils.verifyPassword

class UserSignInByUserEmailUserCase(
        private val userDao: UserDao,
) {
    suspend operator fun invoke(email: String, userPassword: String): BaseResponse<UserResponseWithToken> {

        if (
                email.isEmpty() ||
                userPassword.isEmpty()
        ) {
            return BaseResponse.WithIssueResponse(
                    message = ResponseMessages.EmptyField.message,
                    data = UserResponseWithToken()
            )
        }

        if (!userDao.checkIfUserEmailExist(email)) {
            return BaseResponse.WithIssueResponse(
                    message = ResponseMessages.NotFoundUserEmail.message,
                    data = UserResponseWithToken()
            )
        }

        val userDto = userDao.getUserByUserEmail(email = email)

        val validationPassword =
                SaltedHash(hash = userDto.userPassword, salt = userDto.userSalt).verifyPassword(userPassword)

        if (!validationPassword) {
            return BaseResponse.WithIssueResponse(
                    message = ResponseMessages.IncorrectPassword.message,
                    data = UserResponseWithToken()
            )
        }

        return BaseResponse.SuccessResponse(
                data = UserResponseWithToken(
                        token = userDto.userId.generateToken(),
                        userID = userDto.userId,
                        userName = userDto.userName,
                        pojoPrice = userDto.pojoPrice
                ),
                message = ResponseMessages.SuccessSignIn.message
        )
    }
}