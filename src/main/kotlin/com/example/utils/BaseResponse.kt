package com.example.utils

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.ktor.http.*

@JsonSerialize
sealed class BaseResponse<T>(@JsonIgnore open val statuesCode: HttpStatusCode) {

    @JsonSerialize
    data class SuccessResponse<T>(
        val status: Boolean = true,
        val data: T?,
        val message: String = ResponseMessages.SuccessFetchImageDetails.message,
        val statusCode: HttpStatusCode = HttpStatusCode.OK,
    ) : BaseResponse<T>(statusCode)

    @JsonSerialize
    data class ErrorLiseResponse<T>(
        val status: Boolean = false,
        val data: List<T> = emptyList(),
        val message: String,
        val statusCode: HttpStatusCode = HttpStatusCode.OK,
    ) : BaseResponse<T>(statusCode)

    @JsonSerialize
    data class WithIssueResponse<T>(
        val status: Boolean = false,
        val data: T?,
        val message: String,
        val statusCode: HttpStatusCode = HttpStatusCode.OK,
    ) : BaseResponse<T>(statusCode)
    @JsonSerialize
    data class EmptyListResponse<T>(
            val status: Boolean = false,
            val data: List<T> = emptyList(),
            val message: String,
            val statusCode: HttpStatusCode = HttpStatusCode.OK,
    ) : BaseResponse<T>(statusCode)
    @JsonSerialize
    data class SuccessSignResponse<T>(
        val status: Boolean = true,
        val data: T?,
        val message: String = ResponseMessages.SuccessSignIn.message,
        val statusCode: HttpStatusCode = HttpStatusCode.OK,
    ) : BaseResponse<T>(statusCode)
}