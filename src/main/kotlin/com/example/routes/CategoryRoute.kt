package com.example.routes

import com.example.data.dto.imageDetails.ImageCategoryDto
import com.example.data.dto.imageDetails.ImageDetailsDto
import com.example.domain.endpoints.PojoEndPoint
import com.example.domain.usecases.category.GetAllCategoriesUseCase
import com.example.domain.usecases.category.GetAllLiteCategoriesUserCase
import com.example.utils.BaseResponse
import com.example.utils.handelExceptionsForListResponse
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.categoriesRoute() {
    val getAllCategoriesUseCase: GetAllCategoriesUseCase by inject<GetAllCategoriesUseCase>()
    val getAllLiteCategories: GetAllLiteCategoriesUserCase by inject<GetAllLiteCategoriesUserCase>()

    /**
    Images Category
     */
    get(PojoEndPoint.AllCategories.path) {
        handelExceptionsForListResponse<List<ImageDetailsDto>> {
            val categories = getAllCategoriesUseCase()
            call.respond(message = categories, status = categories.statuesCode)
        }
    }

    get(path = PojoEndPoint.AllLiteCategories.path) {
        handelExceptionsForListResponse<List<ImageDetailsDto>> {
            val categories: BaseResponse<List<ImageCategoryDto>> = getAllLiteCategories()
            call.respond(message = categories, status = categories.statuesCode)
        }
    }
}