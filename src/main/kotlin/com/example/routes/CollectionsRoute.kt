package com.example.routes

import com.example.data.dto.collections.CollectionDetailsForCreateNewOne
import com.example.data.dto.collections.CollectionWithUserDto
import com.example.domain.endpoints.PojoEndPoint
import com.example.domain.usecases.collections.*
import com.example.domain.usecases.image.DeleteImageFromCollectionUseCase
import com.example.routes.mapper.image.pagingParameter
import com.example.utils.handelExceptions
import com.example.utils.handelExceptionsForListResponse
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import org.koin.ktor.ext.inject

fun Route.collectionsRoute() {

    val listOfUsersCollectionsUseCase by inject<GetUsersCollectionsUseCase>()
    val imagesFromUsersCollectionsUseCase by inject<GetImagesUserCollectionsUseCase>()
    val userCollectionDetailsUseCase by inject<GetUserCollectionDetailsUseCase>()
    val userCollectionsByUserIdUseCase by inject<GetUserCollectionsByUserIdUseCase>()
    val updateUserLikeUserCollection by inject<UpdateUserLikeUserCollection>()
    val getALlCollectionTitleImagesUseCase by inject<GetALlCollectionTitleProfileImagesUseCase>()
    val removeImageCollectionForUserCollectionUseCase by inject<RemoveImageCollectionForUserCollectionUseCase>()
    val updateImageNumberToUserCollectionUseCase by inject<UpdateImageNumberToUserCollectionUseCase>()
    val updateUserCollectionTitleImageUseCase by inject<UpdateUserCollectionTitleImageUseCase>()
    val updateUserCollectionTitleTextUseCase by inject<UpdateUserCollectionTitleTextUseCase>()
    val createUserCollectionUseCase by inject<CreateUserCollectionUseCase>()
    val addImageToCollectionForUserCollectionsUseCase by inject<AddImageToCollectionForUserCollectionsUseCase>()
    val updateCollectionVisibilityUseCase by inject<UpdateCollectionVisibilityUseCase>()
    val deleteUserCollectionUseCase by inject<DeleteUserCollectionUseCase>()
    val deleteImageFromCollectionUseCase by inject<DeleteImageFromCollectionUseCase>()
    val getCollectionImagesWithImageDetailsUseCase by inject<GetCollectionImagesWithImageOneImageDetailsUseCase>()

    get(path = PojoEndPoint.UsersCollections.path) {
        handelExceptionsForListResponse<List<CollectionWithUserDto>> {
            val parameters = pagingParameter()
            val invisibility = call.request.queryParameters.getOrFail(name = "is_visible").toBoolean()
            val minimumNumberOfImages = call.request.queryParameters.getOrFail(name = "minimum_number_images").toInt()
            val maximumNumberOfImages = call.request.queryParameters.getOrFail(name = "maximum_number_images").toInt()
            val collections = listOfUsersCollectionsUseCase(
                pageSize = parameters.pageSize.toInt(),
                page = parameters.pageNum.toInt(),
                userId = parameters.userId,
                invisibility = invisibility,
                minimumNumberOfImages = minimumNumberOfImages,
                maximumNumberOfImages = maximumNumberOfImages
            )
            call.respond(message = collections, status = collections.statuesCode)
        }
    }

    put(path = PojoEndPoint.ImagesByUsersCollections.path) {
        handelExceptionsForListResponse<List<CollectionWithUserDto>> {
            val collectionId = call.request.queryParameters.getOrFail("collection_id")
            val images = imagesFromUsersCollectionsUseCase(collectionId = collectionId.toInt())
            call.respond(message = images, status = images.statuesCode)
        }
    }

    put(path = PojoEndPoint.UserCollectionDetails.path) {
        handelExceptionsForListResponse<List<CollectionWithUserDto>> {
            val collectionId = call.request.queryParameters.getOrFail("collection_id")
            val userId = call.request.queryParameters.getOrFail("user_id").toInt()
            val collection = userCollectionDetailsUseCase(
                collectionId = collectionId.toInt(),
                userId = userId
            )
            call.respond(message = collection, status = collection.statuesCode)
        }
    }

    put(path = PojoEndPoint.SpecificUserCollectionsByUserId.path) {
        handelExceptionsForListResponse<List<CollectionWithUserDto>> {
            val userId = call.request.queryParameters.getOrFail("user_id")
            val collections = userCollectionsByUserIdUseCase(userId = userId.toInt())
            call.respond(message = collections, status = collections.statuesCode)
        }
    }

    put(PojoEndPoint.UpdateUserLikeUserCollection.path) {
        handelExceptions {
            val collectionId = call.request.queryParameters.getOrFail("collection_id").toInt()
            val userId = call.request.queryParameters.getOrFail("user_id").toInt()
            val updateCollections = updateUserLikeUserCollection(userId = userId, collectionId = collectionId)
            call.respond(message = updateCollections, status = updateCollections.statuesCode)
        }
    }

    get(PojoEndPoint.GetAllCollectionTitleImagesEndPoint.path) {
        handelExceptionsForListResponse<List<CollectionWithUserDto>> {
            val parameters = pagingParameter()
            val images = getALlCollectionTitleImagesUseCase(
                userId = parameters.userId,
                pageSize = parameters.pageSize.toInt(),
                pageNumber = parameters.pageNum.toInt()
            )
            call.respond(message = images, status = images.statuesCode)
        }
    }

    put(PojoEndPoint.RemoveImageCollectionForUserCollectionEndPoint.path) {
        handelExceptions {
            val collectionId = call.request.queryParameters.getOrFail("collection_id").toInt()
            val imageId = call.request.queryParameters.getOrFail("image_id").toInt()
            val remove = removeImageCollectionForUserCollectionUseCase(
                collectionId = collectionId,
                imageId = imageId
            )
            call.respond(message = remove, status = remove.statuesCode)
        }
    }

    put(PojoEndPoint.UpdateImageNumberToUserCollectionEndPoint.path) {
        handelExceptions {
            val collectionId = call.request.queryParameters.getOrFail("collection_id").toInt()
            val isIncrease = call.request.queryParameters.getOrFail("is_increase").toBoolean()
            val update = updateImageNumberToUserCollectionUseCase(
                collectionId = collectionId,
                isIncrease = isIncrease
            )
            call.respond(message = update, status = update.statuesCode)
        }
    }

    put(PojoEndPoint.UpdateUserCollectionTitleImageEndPoint.path) {
        handelExceptions {
            val collectionId = call.request.queryParameters.getOrFail("collection_id").toInt()
            val collectionTitleId = call.request.queryParameters.getOrFail("collection_title_id").toInt()
            val update = updateUserCollectionTitleImageUseCase(
                collectionId = collectionId,
                collectionTitleId = collectionTitleId
            )
            call.respond(message = update, status = update.statuesCode)
        }
    }

    put(PojoEndPoint.UpdateUserCollectionTitleTextEndPoint.path) {
        handelExceptions {
            val collectionId = call.request.queryParameters.getOrFail("collection_id").toInt()
            val title = call.request.queryParameters.getOrFail("title")
            val update = updateUserCollectionTitleTextUseCase(
                collectionId = collectionId,
                title = title
            )
            call.respond(message = update, status = update.statuesCode)
        }
    }

    put(PojoEndPoint.CreateUserCollectionEndPoint.path) {
        handelExceptions {
            val userId = call.request.queryParameters.getOrFail("user_id").toInt()
            val title = call.request.queryParameters.getOrFail("title")
            val isVisible = call.request.queryParameters.getOrFail("is_visible").toBoolean()
            val create = createUserCollectionUseCase(
                CollectionDetailsForCreateNewOne(
                    user_id = userId,
                    collection_name = title,
                    collection_invisibility = isVisible
                )
            )
            call.respond(message = create, status = create.statuesCode)
        }
    }

    put(PojoEndPoint.AddImageToCollectionForUserCollectionsEndPoint.path) {
        handelExceptions {
            val collectionId = call.request.queryParameters.getOrFail("collection_id").toInt()
            val imageId = call.request.queryParameters.getOrFail("image_id").toInt()
            val add = addImageToCollectionForUserCollectionsUseCase(
                collectionId = collectionId,
                imageId = imageId
            )
            call.respond(message = add, status = add.statuesCode)
        }
    }

    put(PojoEndPoint.UpdateCollectionVisibilityEndPoint.path) {
        handelExceptions {
            val collectionId = call.request.queryParameters.getOrFail("collection_id").toInt()
            val visibility = call.request.queryParameters.getOrFail("visibility").toBoolean()
            val update = updateCollectionVisibilityUseCase(
                collectionId = collectionId,
                visibility = visibility
            )
            call.respond(message = update, status = update.statuesCode)
        }
    }

    put(PojoEndPoint.DeleteCollectionEndPoint.path) {
        handelExceptions {
            val collectionId = call.request.queryParameters.getOrFail("collection_id").toInt()
            val update = deleteUserCollectionUseCase(
                collectionId = collectionId
            )
            call.respond(message = update, status = update.statuesCode)
        }
    }

    put(PojoEndPoint.DeleteImageFromCollectionEndPoint.path) {
        handelExceptions {
            val collectionId = call.request.queryParameters.getOrFail("collection_id").toInt()
            val imageId = call.request.queryParameters.getOrFail("image_id").toInt()
            val deleteImage = deleteImageFromCollectionUseCase(
                collectionId = collectionId,
                imageId = imageId
            )
            call.respond(message = deleteImage, status = deleteImage.statuesCode)
        }
    }

    put(PojoEndPoint.GetCollectionImagesWithOneImageDetails.path) {
        handelExceptionsForListResponse<List<CollectionWithUserDto>> {
            val collectionId = call.request.queryParameters.getOrFail("collection_id").toInt()
            val imageId = call.request.queryParameters.getOrFail("image_id").toInt()
            val userId = call.request.queryParameters.getOrFail("user_id").toInt()
            val images = getCollectionImagesWithImageDetailsUseCase(
                collectionId = collectionId,
                imageId = imageId,
                userId = userId
            )
            call.respond(message = images, status = images.statuesCode)
        }
    }
}