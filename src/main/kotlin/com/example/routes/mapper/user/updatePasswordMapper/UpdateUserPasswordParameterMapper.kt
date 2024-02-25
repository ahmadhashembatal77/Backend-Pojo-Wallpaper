package com.example.routes.mapper.user.updatePasswordMapper

import com.example.data.request.UpdateUserPasswordRequest
import io.ktor.server.application.*
import io.ktor.server.util.*
import io.ktor.util.pipeline.*

fun PipelineContext<*, ApplicationCall>.userUpdatePasswordParameters(): UpdateUserPasswordRequest {
    val userParameters = call.request.queryParameters
    return UpdateUserPasswordRequest(
        username = userParameters.getOrFail("name"),
        password = userParameters.getOrFail("password"),
        newPassword = userParameters.getOrFail("newPassword")
    )
}