package com.example.routes

import com.example.data.dto.collections.CollectionWithUserDto
import com.example.domain.endpoints.PojoEndPoint
import com.example.domain.usecases.stickers.*
import com.example.routes.mapper.image.pagingParameter
import com.example.utils.handelExceptions
import com.example.utils.handelExceptionsForListResponse
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import org.koin.ktor.ext.inject

fun Route.stickersRoute() {

    val getStickersByDateUseCase by inject<GetStickersByDateUseCase>()
    val updateStickerLikeBasedOnLikeTypeUseCase by inject<UpdateStickerLikeBasedOnLikeTypeUseCase>()
    val getLimitAllStickerCollectionsUseCase by inject<GetLimitAllStickerCollectionsUseCase>()
    val getStickersFromCollectionUseCase by inject<GetStickersFromCollectionUseCase>()
    val insertStickerToUserStickerOwnedUseCase by inject<InsertStickerToUserStickerOwnedUseCase>()
    val getLimitAllOwnedUserStickersUseCase by inject<GetLimitAllOwnedUserStickersUseCase>()

    get(PojoEndPoint.GetStickersByDateEndPoint.path) {
        handelExceptionsForListResponse<List<CollectionWithUserDto>> {
            val parameters = pagingParameter()
            val stickers = getStickersByDateUseCase(
                pageSize = parameters.pageSize.toInt(),
                pageNumber = parameters.pageNum.toInt(),
                userId = parameters.userId
            )
            call.respond(message = stickers, status = stickers.statuesCode)
        }
    }

    get(PojoEndPoint.GetAllStickerCollectionsEndPoint.path) {
        handelExceptionsForListResponse<List<CollectionWithUserDto>> {
            val limit = call.request.queryParameters.getOrFail(name = "limit").toInt()
            val stickerCollections = getLimitAllStickerCollectionsUseCase(collectionLimit = limit)
            call.respond(message = stickerCollections, status = stickerCollections.statuesCode)
        }
    }

    get(PojoEndPoint.GetAllStickersFromCollectionsEndPoint.path) {
        handelExceptionsForListResponse<List<CollectionWithUserDto>> {
            val collectionId = call.request.queryParameters.getOrFail("sticker_collection_id").toInt()
            val userId = call.request.queryParameters.getOrFail("user_id").toInt()
            val stickers = getStickersFromCollectionUseCase(collectionId = collectionId, userId = userId)
            call.respond(message = stickers, status = stickers.statuesCode)
        }
    }

    get(PojoEndPoint.GetLimitedUserOwnedStickers.path) {
        handelExceptionsForListResponse<List<CollectionWithUserDto>> {
            val userId = call.request.queryParameters.getOrFail("user_id").toInt()
            val limit = call.request.queryParameters.getOrFail("limit").toInt()
            val stickers = getLimitAllOwnedUserStickersUseCase(userId = userId, limit = limit)
            call.respond(message = stickers, status = stickers.statuesCode)
        }
    }

    put(PojoEndPoint.InsertStickerToUserOwnedEndPoint.path) {
        handelExceptions {
            val stickerId = call.request.queryParameters.getOrFail(name = "sticker_id").toInt()
            val userId = call.request.queryParameters.getOrFail(name = "user_id").toInt()
            val price = call.request.queryParameters.getOrFail(name = "price").toInt()
            val updateSticker = insertStickerToUserStickerOwnedUseCase(
                stickerId = stickerId,
                userId = userId,
                price = price
            )
            call.respond(message = updateSticker, status = updateSticker.statuesCode)
        }
    }

    put(PojoEndPoint.UpdateStickerLikeEndPoint.path) {
        handelExceptions {
            val stickerId = call.request.queryParameters.getOrFail("sticker_id").toInt()
            val likeType = call.request.queryParameters.getOrFail("like_type").toInt()
            val updateSticker = updateStickerLikeBasedOnLikeTypeUseCase(stickerId = stickerId, likeType = likeType)
            call.respond(message = updateSticker, status = updateSticker.statuesCode)
        }
    }
}