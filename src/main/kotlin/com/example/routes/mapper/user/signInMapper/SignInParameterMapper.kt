package com.example.routes.mapper.user.signInMapper

import com.example.data.request.UserNameAndPasswordRequest
import io.ktor.server.application.*
import io.ktor.server.util.*
import io.ktor.util.pipeline.*

fun PipelineContext<*, ApplicationCall>.userNameAndPasswordRequest(): UserNameAndPasswordRequest {
    val userParameters = call.request.queryParameters
    return UserNameAndPasswordRequest(
        usernameOrEmail = userParameters.getOrFail("nameOrEmail"),
        password = userParameters.getOrFail("password"),
    )
}