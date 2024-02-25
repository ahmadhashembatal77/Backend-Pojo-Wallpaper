package com.example.data.request

data class ImagesByCategoryRequest(
    val pageSize : Int,
    val page: Int,
    val categoryId: Int,
    val categoryName : String
)