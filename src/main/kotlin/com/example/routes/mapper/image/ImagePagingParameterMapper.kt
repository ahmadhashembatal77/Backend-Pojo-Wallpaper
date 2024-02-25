package com.example.routes.mapper.image

import com.example.data.request.ImageCategoryColorRequest
import com.example.data.request.ImagePaging
import com.example.data.request.ImagesByCategoryRequest
import io.ktor.server.application.*
import io.ktor.server.util.*
import io.ktor.util.pipeline.*

fun PipelineContext<*, ApplicationCall>.pagingParameter(): ImagePaging {
    val callParameters = call.request.queryParameters
    return ImagePaging(
        pageSize = callParameters.getOrFail("page_size"),
        pageNum = callParameters.getOrFail("page_number"),
        userId = callParameters.getOrFail("user_id").toInt()
    )
}

fun PipelineContext<*, ApplicationCall>.imageCategoryColorParameters(): ImageCategoryColorRequest {
    val callParameters = call.request.queryParameters
    return ImageCategoryColorRequest(
        imageId = callParameters.getOrFail("image_id").toInt(),
        categoryId = callParameters.getOrFail("category_id").toInt(),
        userId = callParameters.getOrFail("user_id").toInt()
    )
}

fun PipelineContext<*, ApplicationCall>.imagesByCategoryParameters(): ImagesByCategoryRequest {
    val callParameters = call.request.queryParameters
    return ImagesByCategoryRequest(
        pageSize = callParameters.getOrFail("page_size").toInt(),
        page = callParameters.getOrFail("page_number").toInt(),
        categoryId = callParameters.getOrFail("category_id").toInt(),
        categoryName = callParameters.getOrFail("category_name")
    )
}