package com.example.domain.usecases.user

import com.example.data.dto.user.UserDto
import com.example.data.response.UserResponseWithToken
import com.example.data.source.dao.UserDao
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages
import com.example.utils.generateSaltedHash
import com.example.utils.generateToken

class SignUpUseCase(
        private val userDao: UserDao,
) {
    suspend operator fun invoke(username: String, password: String): BaseResponse<UserResponseWithToken> {

        if (username.isEmpty() || password.isEmpty()) {
            return BaseResponse.WithIssueResponse(
                    message = ResponseMessages.EmptyField.message,
                    data = UserResponseWithToken()
            )
        }

        if (userDao.checkIfUserNameExist(username = username)) {
            return BaseResponse.WithIssueResponse(
                    message = ResponseMessages.UserNameAlreadyExist.message,
                    data = UserResponseWithToken()
            )
        }
        val userPasswordAndSalt = password.generateSaltedHash()
        val userInsert: UserDto = userDao.insertUser(username, userPasswordAndSalt.hash, userPasswordAndSalt.salt)
        return BaseResponse.SuccessResponse(
                message = ResponseMessages.SuccessSignup.message,
                data = UserResponseWithToken(
                        userID = userInsert.user_id,
                        userName = userInsert.user_name,
                        token = userInsert.user_id.generateToken(),
                        pojoPrice = userInsert.pojo_price
                )
        )
    }
}


/**
 * Will Delete Because We Remove Emails In First Register But We Need This Code Later In Other Class
 * */
//        if (userDao.checkIfUserEmailExist(email = user.user_email)) {
//            return BaseResponse.WithIssueResponse(
//                    message = ResponseMessages.UserEmailIsAlreadyExist.message,
//                    data = UserResponseWithToken()
//            )
//        }