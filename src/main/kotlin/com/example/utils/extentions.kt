package com.example.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.domain.SaltedHash
import com.example.utils.Constants.AUDIENCE
import com.example.utils.Constants.ISSUER
import com.example.utils.Constants.REFRESH_TOKEN_EXPIRE_DATE
import com.example.utils.Constants.SALTED_HASH_ALGORITHM
import com.example.utils.Constants.SECRET
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.util.pipeline.*
import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils
import java.security.SecureRandom
import java.text.SimpleDateFormat
import java.util.*

//fun String.stringToLocalDateTime(): LocalDateTime {
//    return LocalDateTime.parse(this, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
//}

fun String.generateSaltedHash(): SaltedHash {
    // Get Random Salt Number
    val salt = SecureRandom.getInstance(SALTED_HASH_ALGORITHM).generateSeed(32)
    // Hex Encoder for Salt
    val saltAsHex = Hex.encodeHexString(salt)
    // Hash SaltAsHex With User Password
    // Now, We Have
    // Hash( Hex(salt) + value)
    val hash = DigestUtils.sha256Hex("$saltAsHex$this")
    return SaltedHash(hash = hash, salt = saltAsHex)
}

/**
 *  Integer Should Be UserId
 */
fun Int.generateToken(): String =
    JWT.create()
        .withAudience(AUDIENCE)
        .withIssuer(ISSUER)
        .withClaim("user_id", this)
        .withExpiresAt(Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRE_DATE))
        .sign(Algorithm.HMAC256(SECRET))

fun SaltedHash.verifyPassword(password: String): Boolean {
    return DigestUtils.sha256Hex(salt + password) == hash
}

fun Long.convertLongToDate(): String {
    val date = Date(this)
    val format = SimpleDateFormat("yyyy/M/dd")
    return format.format(date)
}

suspend fun PipelineContext<*, ApplicationCall>.handelExceptions(block: suspend () -> Unit) {
    try {
        handelHeader()
        block()
    } catch (e: Exception) {
        val errorResponse: BaseResponse.SuccessResponse<Boolean> = BaseResponse.SuccessResponse(
            status = false,
            message = e.message.toString(),
            data = false
        )
        this.call.respond(message = errorResponse, status = errorResponse.statusCode)
    }
}

suspend fun <T> PipelineContext<*, ApplicationCall>.handelExceptionsForListResponse(block: suspend () -> Unit) {
    try {
        handelHeaderForList<T>()
        block()
    } catch (e: Exception) {
        val errorResponse = BaseResponse.EmptyListResponse<T>(
            status = false,
            message = e.message.toString(),
            data = emptyList()
        )
        this.call.respond(message = errorResponse, status = errorResponse.statusCode)
    }
}

private suspend fun <T> PipelineContext<*, ApplicationCall>.handelHeaderForList() {
    val header: String? = call.request.headers["pojo_api_key"]
    if (header != "XxGg1j2l3ks&*&53GLghL") {
        val errorResponse = BaseResponse.EmptyListResponse<T>(
            status = false,
            message = "Not Found Right Header:-)",
            data = emptyList()
        )
        this.call.respond(message = errorResponse, status = errorResponse.statusCode)
        return
    }
}

private suspend fun PipelineContext<*, ApplicationCall>.handelHeader() {
    val header: String? = call.request.headers["pojo_api_key"]
    if (header != "XxGg1j2l3ks&*&53GLghL") {
        val errorResponse: BaseResponse.SuccessResponse<Boolean> = BaseResponse.SuccessResponse(
            status = false,
            message = "Not Found Right Header:-)",
            data = false
        )
        call.respond(message = errorResponse, status = errorResponse.statusCode)
        return
    }
}