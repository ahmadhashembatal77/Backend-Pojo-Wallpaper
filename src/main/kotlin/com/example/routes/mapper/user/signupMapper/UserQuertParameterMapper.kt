package com.example.routes.mapper.user.signupMapper

import com.example.data.request.UserRegisterRequest
import io.ktor.server.application.*
import io.ktor.server.util.*
import io.ktor.util.pipeline.*

fun PipelineContext<*, ApplicationCall>.userParameters(): UserRegisterRequest {
    val userParameters = call.request.queryParameters
    return UserRegisterRequest(
        username = userParameters.getOrFail("user_name"),
        password = userParameters.getOrFail("password")
    )
}