package com.example.domain.usecases.user

import com.example.data.request.UpdateUserPasswordRequest
import com.example.data.source.dao.UserDao
import com.example.domain.SaltedHash
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages
import com.example.utils.generateSaltedHash
import com.example.utils.verifyPassword

class UpdateUserPasswordUseCase(
        private val userDao: UserDao
) {
    suspend operator fun invoke(parameters: UpdateUserPasswordRequest): BaseResponse<Boolean> {
        if (
                parameters.password.isEmpty() ||
                parameters.newPassword.isEmpty() ||
                parameters.username.isEmpty()
        ) {
            return BaseResponse.WithIssueResponse(message = ResponseMessages.EmptyField.message, data = false)
        }

        val userDto = userDao.getUserByUserName(userName = parameters.username)

        if (userDto.userName == "Empty") {
            return BaseResponse.WithIssueResponse(message = ResponseMessages.NotFoundUser.message, data = false)
        }

        val validationPassword = SaltedHash(hash = userDto.userPassword, salt = userDto.userSalt)
                .verifyPassword(parameters.password)

        if (!validationPassword) {
            return BaseResponse.WithIssueResponse(message = ResponseMessages.IncorrectPassword.message, data = false)
        }

        val saltedHash = parameters.newPassword.generateSaltedHash()
        userDao.updateUserPassword(
                userId = userDto.userId,
                userPassword = saltedHash.hash,
                userSalt = saltedHash.salt
        )
        return BaseResponse.SuccessResponse(
                message = ResponseMessages.SuccessUpdatePassword.message, data = true
        )
    }
}