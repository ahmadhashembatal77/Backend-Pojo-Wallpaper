package com.example.plugins

import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTDecodeException
import com.example.token.TokenConfig
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages
import io.ktor.client.engine.*
import io.ktor.server.application.*
import io.ktor.server.plugins.callid.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.get

fun Application.configureSecurity(config: TokenConfig) {
    authentication {
        jwt {
            realm = "PojoRealm"
            verifier(
                JWT
                    .require(Algorithm.HMAC256(config.secret))
                    .withAudience(config.audience)
                    .withIssuer(config.issuer)
                    .build()
            )
            challenge { _, _ ->
                val tokenError: BaseResponse.WithIssueResponse<String> = BaseResponse.WithIssueResponse(
                    message = ResponseMessages.FailAuthentication.message,
                    data = "Authorization header can not be blank!"
                )
                call.request.headers["Authorization"]?.let {
                    if (it.isNotEmpty()) {
                        val tokenWithIssueResponse = BaseResponse.WithIssueResponse(
                            message = ResponseMessages.FailAuthentication.message,
                            data = "Token expired"
                        )
                        call.respond(message = tokenWithIssueResponse, status = tokenWithIssueResponse.statusCode)
                    } else {
                        val tokenErrorBlank = BaseResponse.WithIssueResponse(
                            message = ResponseMessages.FailAuthentication.message,
                            data = "Authorization header can not be blank!"
                        )
                        call.respond(message = tokenErrorBlank, status = tokenErrorBlank.statusCode)
                    }
                } ?: call.respond(message = tokenError, status = tokenError.statusCode)
            }
            validate { credential ->
                try {
                    if (credential.payload.audience.contains(config.audience)) {
                        JWTPrincipal(credential.payload)
                    } else null
                } catch (e: JWTDecodeException) { null }
            }
        }
    }
}