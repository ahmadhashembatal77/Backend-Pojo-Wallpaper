package com.example.routes

import com.example.domain.endpoints.UserEndPoint
import com.example.domain.usecases.user.*
import com.example.domain.usecases.util.isValidEmail
import com.example.routes.mapper.image.pagingParameter
import com.example.routes.mapper.user.updatePasswordMapper.userUpdatePasswordParameters
import com.example.routes.mapper.user.signInMapper.userNameAndPasswordRequest
import com.example.routes.mapper.user.signupMapper.userParameters
import com.example.utils.BaseResponse
import com.example.utils.handelExceptions
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import org.koin.ktor.ext.inject

fun Route.userRoute() {
    val signUpUseCase by inject<SignUpUseCase>()
    val userSignInByUserNameUseCase by inject<UserSignInByUserNameUseCase>()
    val userSignInByUserEmailUseCase by inject<UserSignInByUserEmailUserCase>()
    val authUserUserCase by inject<AuthUserUserCase>()
    val updateUserPasswordUseCase by inject<UpdateUserPasswordUseCase>()
    val deleteUserUseCase by inject<DeleteUserUseCase>()
    val getUserDetailsByIDUserCase by inject<GetUserDetailsByIDUserCase>()
    val getOwnedUserProfileImagesUseCase by inject<GetOwnedUserProfileImagesUseCase>()
    val getOwnedUserBackgroundImagesUseCase by inject<GetOwnedUserBackgroundImagesUseCase>()
    val getAllUsersProfileImagesUseCase by inject<GetAllUsersProfileImagesUseCase>()
    val getAllUsersBackgroundImagesUseCase by inject<GetAllUsersBackgroundImagesUseCase>()
    val updateUserNameUseCase by inject<UpdateUserNameUseCase>()
    val updateUserImageProfileUseCase by inject<UpdateUserImageProfileUseCase>()
    val updateUserImageBackgroundUseCase by inject<UpdateUserImageBackgroundUseCase>()
    val makeUserBuyCollectionTitleImageUseCase by inject<MakeUserBuyCollectionTitleImageUseCase>()
    val makeUserBuyProfileImageUseCase by inject<MakeUserBuyProfileImageUseCase>()
    val makeUserBuyUserBackgroundImageUseCase by inject<MakeUserBuyUserBackgroundImageUseCase>()
    val updateUserPojoPriceUseCase by inject<UpdateUserPojoPriceUseCase>()

    post(UserEndPoint.SignUp.path) {
        handelExceptions {
            val userParameters = userParameters()
            val signUpUser = signUpUseCase(
                username = userParameters.username,
                password = userParameters.password
            )
            call.respond(message = signUpUser, status = signUpUser.statuesCode)
        }
    }

    post(UserEndPoint.SignIn.path) {
        handelExceptions {
            val signInParameters = userNameAndPasswordRequest()
            if (signInParameters.usernameOrEmail.isValidEmail()) {
                val signInToken = userSignInByUserEmailUseCase(
                    signInParameters.usernameOrEmail,
                    signInParameters.password
                )
                call.respond(message = signInToken, status = signInToken.statuesCode)
            } else {
                val signInToken = userSignInByUserNameUseCase(
                    signInParameters.usernameOrEmail, signInParameters.password
                )
                call.respond(message = signInToken, status = signInToken.statuesCode)
            }
        }
    }

    authenticate {
        get(UserEndPoint.Authenticate.path) {
            val auth: BaseResponse<String> = authUserUserCase()
            call.respond(message = auth, status = auth.statuesCode)
        }
    }

    get(UserEndPoint.DeleteUserByUsernameAndPassword.path) {
        handelExceptions {
            val signInParameters = userNameAndPasswordRequest()
            val signInToken = deleteUserUseCase(signInParameters.usernameOrEmail, signInParameters.password)
            call.respond(message = signInToken, status = signInToken.statuesCode)
        }
    }

    get(UserEndPoint.UpdatePassword.path) {
        handelExceptions {
            val userParameters = userUpdatePasswordParameters()
            val updateUserPassword = updateUserPasswordUseCase(userParameters)
            call.respond(message = updateUserPassword, status = updateUserPassword.statuesCode)
        }
    }

    put(UserEndPoint.UserDetailsByUserID.path) {
        handelExceptions {
            val userId = call.request.queryParameters.getOrFail("user_id").toInt()
            val userDetails = getUserDetailsByIDUserCase(userId)
            call.respond(message = userDetails, status = userDetails.statuesCode)
        }
    }

    put(UserEndPoint.GetOwnedUserProfileImagesEndPoint.path) {
        handelExceptions {
            val userId = call.request.queryParameters.getOrFail("user_id").toInt()
            val images = getOwnedUserProfileImagesUseCase(userId)
            call.respond(message = images, status = images.statuesCode)
        }
    }

    put(UserEndPoint.GetOwnedUserBackgroundImagesEndPoint.path) {
        handelExceptions {
            val userId = call.request.queryParameters.getOrFail("user_id").toInt()
            val images = getOwnedUserBackgroundImagesUseCase(userId)
            call.respond(message = images, status = images.statuesCode)
        }
    }

    put(UserEndPoint.GetAllUsersProfileImagesEndPoint.path) {
        handelExceptions {
            val parameters = pagingParameter()
            val images = getAllUsersProfileImagesUseCase(
                userId = parameters.userId,
                pageSize = parameters.pageSize.toInt(),
                pageNumber = parameters.pageNum.toInt()
            )
            call.respond(message = images, status = images.statuesCode)
        }
    }

    put(UserEndPoint.GetAllUsersBackgroundImagesEndPoint.path) {
        handelExceptions {
            val parameters = pagingParameter()
            val images = getAllUsersBackgroundImagesUseCase(
                userId = parameters.userId,
                pageSize = parameters.pageSize.toInt(),
                pageNumber = parameters.pageNum.toInt()
            )
            call.respond(message = images, status = images.statuesCode)
        }
    }

    put(UserEndPoint.UpdateUserNameEndPoint.path) {
        handelExceptions {
            val userId = call.request.queryParameters.getOrFail("user_id").toInt()
            val userName = call.request.queryParameters.getOrFail("user_name")
            val update = updateUserNameUseCase(userId = userId, userName = userName)
            call.respond(message = update, status = update.statuesCode)
        }
    }

    put(UserEndPoint.UpdateUserImageProfileEndPoint.path) {
        handelExceptions {
            val userId = call.request.queryParameters.getOrFail("user_id").toInt()
            val userProfileId = call.request.queryParameters.getOrFail("user_profile_id").toInt()
            val update = updateUserImageProfileUseCase(userId, profileImageId = userProfileId)
            call.respond(message = update, status = update.statuesCode)
        }
    }

    put(UserEndPoint.UpdateUserImageBackgroundEndPoint.path) {
        handelExceptions {
            val userId = call.request.queryParameters.getOrFail("user_id").toInt()
            val backgroundId = call.request.queryParameters.getOrFail("background_id").toInt()
            val update = updateUserImageBackgroundUseCase(userId = userId, backgroundId = backgroundId)
            call.respond(message = update, status = update.statuesCode)
        }
    }

    put(UserEndPoint.MakeUserBuyCollectionTitleImageEndPoint.path) {
        handelExceptions {
            val userId = call.request.queryParameters.getOrFail("user_id").toInt()
            val imageId = call.request.queryParameters.getOrFail("image_id").toInt()
            val price = call.request.queryParameters.getOrFail("price").toInt()
            val update = makeUserBuyCollectionTitleImageUseCase(
                userId = userId,
                collectionTitleImageId = imageId,
                price = price
            )
            call.respond(message = update, status = update.statuesCode)
        }
    }

    put(UserEndPoint.MakeUserBuyProfileImageEndPoint.path) {
        handelExceptions {
            val userId = call.request.queryParameters.getOrFail("user_id").toInt()
            val imageId = call.request.queryParameters.getOrFail("image_id").toInt()
            val price = call.request.queryParameters.getOrFail("price").toInt()
            val update = makeUserBuyProfileImageUseCase(
                userId = userId,
                profileImageId = imageId,
                price = price
            )
            call.respond(message = update, status = update.statuesCode)
        }
    }

    put(UserEndPoint.MakeUserBuyUserBackgroundImageEndPoint.path) {
        handelExceptions {
            val userId = call.request.queryParameters.getOrFail("user_id").toInt()
            val imageId = call.request.queryParameters.getOrFail("image_id").toInt()
            val price = call.request.queryParameters.getOrFail("price").toInt()
            val update = makeUserBuyUserBackgroundImageUseCase(
                userId = userId,
                backgroundImageId = imageId,
                price = price
            )
            call.respond(message = update, status = update.statuesCode)
        }
    }

    put(UserEndPoint.UpdateUserPojoPriceEndPoint.path) {
        handelExceptions {
            val userId = call.request.queryParameters.getOrFail("user_id").toInt()
            val increase = call.request.queryParameters.getOrFail("increase").toBoolean()
            val price = call.request.queryParameters.getOrFail("price").toInt()
            val update = updateUserPojoPriceUseCase(
                userId = userId,
                increase = increase,
                price = price
            )
            call.respond(message = update, status = update.statuesCode)
        }
    }
}