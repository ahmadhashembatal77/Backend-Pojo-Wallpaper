package com.example.domain.usecases.user

import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class AuthUserUserCase {
     operator fun invoke():BaseResponse<String>{
        return BaseResponse.SuccessResponse(data = ResponseMessages.SuccessAuthentication.message)
    }
}