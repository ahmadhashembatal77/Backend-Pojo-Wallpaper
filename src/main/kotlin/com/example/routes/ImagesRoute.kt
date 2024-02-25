package com.example.routes

import com.example.data.dto.imageDetails.ImageDetailsDto
import com.example.data.dto.imageDetails.ImageDetailsWithUserLikeAndPrimaryDto
import com.example.data.dto.tags.TagsDto
import com.example.data.request.ImageCategoryColorRequest
import com.example.data.request.ImagePaging
import com.example.data.request.ImagesByCategoryRequest
import com.example.domain.endpoints.PojoEndPoint
import com.example.domain.usecases.image.*
import com.example.routes.mapper.image.imageCategoryColorParameters
import com.example.routes.mapper.image.imagesByCategoryParameters
import com.example.routes.mapper.image.pagingParameter
import com.example.utils.BaseResponse
import com.example.utils.handelExceptions
import com.example.utils.handelExceptionsForListResponse
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import org.koin.ktor.ext.inject

fun Route.imagesRoute() {
    val getTopRatedLiteImagesThreeWeeksAgoUseCase: GetTopRatedLiteImagesThreeWeeksAgoUseCase by inject<GetTopRatedLiteImagesThreeWeeksAgoUseCase>()
    val listTopRatedImages: GetListOfTopRatedLiteImages by inject<GetListOfTopRatedLiteImages>()
    val listLiteImagesByCategoryUseCase: GetAllLiteImagesByCategory by inject<GetAllLiteImagesByCategory>()
    val listOfImagesDetailsUseCase: Get60ImagesDependOnCategory by inject<Get60ImagesDependOnCategory>()
    val listOfLiteImagesDetailsByDateUseCase: GetLiteImagesOrderByDateUseCase by inject<GetLiteImagesOrderByDateUseCase>()
    val updateImageUseCase: UpdateUserLikedImage by inject<UpdateUserLikedImage>()
    val updateWatchImageCount: UpdateWatchImageCountUseCase by inject<UpdateWatchImageCountUseCase>()
    val getSearchImageByTextUseCase: GetSearchImageByTextUseCase by inject<GetSearchImageByTextUseCase>()
    val getAllLeastUserLikesImagesUserCase: GetLastUserImagesLikesUseCase by inject<GetLastUserImagesLikesUseCase>()
    val getImageTagsDependOfImageId: GetListOfTagsImageForImageUseCase by inject<GetListOfTagsImageForImageUseCase>()
    val addPrimaryImageToUserOwnedUserCase: AddPrimaryImageToUserOwnedUserCase by inject<AddPrimaryImageToUserOwnedUserCase>()
    val getAllUserOwnedPrimaryImageUseCase: GetAllUserOwnedPrimaryImageUseCase by inject<GetAllUserOwnedPrimaryImageUseCase>()
    val developerMoodUseCase: DeveloperMoodUseCase by inject<DeveloperMoodUseCase>()
//    val developerStickerMoodUseCase: DeveloperMoodStickerUseCase by inject<DeveloperMoodStickerUseCase>()

    get(PojoEndPoint.ThreeWeeksAgoTopRatedImages.path) {
        handelExceptionsForListResponse<List<ImageDetailsDto>> {
            val limit: String = call.request.queryParameters.getOrFail("limit")
            val topRatedList: BaseResponse<List<ImageDetailsDto>> =
                getTopRatedLiteImagesThreeWeeksAgoUseCase(limit = limit.toInt())
            call.respond(
                message = topRatedList,
                status = topRatedList.statuesCode
            )
        }
    }

    put(PojoEndPoint.ListTenTopRated.path) {
        handelExceptionsForListResponse<List<ImageDetailsDto>> {
            val pageSize: String = call.request.queryParameters.getOrFail("page_size")
            val pageNumber: String = call.request.queryParameters.getOrFail("page_number")
            val topRatedList: BaseResponse<List<ImageDetailsDto>> = listTopRatedImages(
                pageSize = pageSize.toInt(),
                pageNumber = pageNumber.toInt()
            )
            call.respond(
                message = topRatedList,
                status = topRatedList.statuesCode
            )
        }
    }

    put(PojoEndPoint.AllLiteImagesByCategory.path) {
        handelExceptionsForListResponse<List<ImageDetailsDto>> {
            val parameters: ImagesByCategoryRequest = imagesByCategoryParameters()
            val images: BaseResponse<List<ImageDetailsWithUserLikeAndPrimaryDto>> = listLiteImagesByCategoryUseCase(
                pageSize = parameters.pageSize,
                page = parameters.page,
                categoryId = parameters.categoryId,
                categoryName = parameters.categoryName
            )
            call.respond(
                message = images,
                status = images.statuesCode
            )
        }
    }

    get(PojoEndPoint.PojoCompleteDetails.path) {
        handelExceptionsForListResponse<List<ImageDetailsDto>> {
            val parameters:ImageCategoryColorRequest = imageCategoryColorParameters()
            val imagesDetails:BaseResponse<List<ImageDetailsWithUserLikeAndPrimaryDto>> = listOfImagesDetailsUseCase(
                imageId = parameters.imageId,
                categoryId = parameters.categoryId,
                userId = parameters.userId
            )
            call.respond(message = imagesDetails, status = imagesDetails.statuesCode)
        }
    }

    put(PojoEndPoint.LiteImagesByDetails.path) {
        handelExceptionsForListResponse<List<ImageDetailsDto>> {
            val pageSize:String = call.request.queryParameters.getOrFail("page_size")
            val pageNumber:String = call.request.queryParameters.getOrFail("page_number")
            val images:BaseResponse<List<ImageDetailsDto>> = listOfLiteImagesDetailsByDateUseCase(
                pageSize = pageSize.toInt(),
                pageNumber = pageNumber.toInt()
            )
            call.respond(message = images, status = images.statuesCode)
        }
    }

    put(PojoEndPoint.UpdateUserLikedPojo.path) {
        handelExceptions {
            val imageId: Int = call.request.queryParameters.getOrFail("image_id").toInt()
            val userId: Int = call.request.queryParameters.getOrFail("user_id").toInt()
            val updateImages: BaseResponse<Boolean> = updateImageUseCase(userId = userId, imageId = imageId)
            call.respond(message = updateImages, status = updateImages.statuesCode)
        }
    }

    put(PojoEndPoint.UpdatePrimaryImageOwnedUser.path) {
        handelExceptions {
            val imageId:Int = call.request.queryParameters.getOrFail("image_id").toInt()
            val userId:Int = call.request.queryParameters.getOrFail("user_id").toInt()
            val price:Int = call.request.queryParameters.getOrFail("price").toInt()
            val addPrimaryImage: BaseResponse<Boolean> = addPrimaryImageToUserOwnedUserCase(
                userId = userId,
                imageId = imageId,
                price = price
            )
            call.respond(message = addPrimaryImage, status = addPrimaryImage.statuesCode)
        }
    }

    put(PojoEndPoint.UpdateWatchPojo.path) {
        handelExceptions {
            val imageId:Int = call.request.queryParameters.getOrFail("image_id").toInt()
            val updateWatch:BaseResponse<Boolean> = updateWatchImageCount(imageId = imageId)
            call.respond(message = updateWatch, status = updateWatch.statuesCode)
        }
    }

    put(PojoEndPoint.SearchPojoByText.path) {
        handelExceptionsForListResponse<List<ImageDetailsDto>> {
            val imageText:String = call.request.queryParameters.getOrFail("image_text")
            val searchResult: BaseResponse<List<ImageDetailsDto>> = getSearchImageByTextUseCase(imageTitle = imageText)
            call.respond(message = searchResult, status = searchResult.statuesCode)
        }
    }

    put(PojoEndPoint.LastUserLikePojo.path) {
        handelExceptionsForListResponse<List<ImageDetailsDto>> {
            val parameters: ImagePaging = pagingParameter()
            val images: BaseResponse<List<ImageDetailsDto>> = getAllLeastUserLikesImagesUserCase(
                pageSize = parameters.pageSize.toInt(),
                pageNumber = parameters.pageNum.toInt(),
                userId = parameters.userId
            )
            call.respond(message = images, status = images.statuesCode)
        }
    }

    put(PojoEndPoint.GetAllOwnedPrimaryImage.path) {
        handelExceptionsForListResponse<List<ImageDetailsDto>> {
            val parameters: ImagePaging = pagingParameter()
            val images: BaseResponse<List<ImageDetailsWithUserLikeAndPrimaryDto>> = getAllUserOwnedPrimaryImageUseCase(
                pageSize = parameters.pageSize.toInt(),
                pageNumber = parameters.pageNum.toInt(),
                userId = parameters.userId
            )
            call.respond(message = images, status = images.statuesCode)
        }
    }
    put(PojoEndPoint.ImageTagsForImage.path) {
        handelExceptionsForListResponse<List<ImageDetailsDto>> {
            val imageId: Int = call.request.queryParameters.getOrFail("image_id").toInt()
            val tags: BaseResponse<List<TagsDto>> = getImageTagsDependOfImageId(imageId = imageId)
            call.respond(message = tags, status = tags.statuesCode)
        }
    }

    put(PojoEndPoint.DeveloperMoodEndPoint.path) {
        handelExceptionsForListResponse<List<ImageDetailsDto>> {
            developerMoodUseCase()
        }
    }
}