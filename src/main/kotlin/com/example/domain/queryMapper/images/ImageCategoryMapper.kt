package com.example.domain.queryMapper.images

import com.example.data.dto.imageDetails.ImageCategoryDto
import com.example.data.tables.ImageCategoriesTable
import com.example.utils.convertLongToDate
import org.ktorm.dsl.QueryRowSet
import java.sql.Date

fun QueryRowSet.imageCategoryMapper() = ImageCategoryDto(
        category_id = this[ImageCategoriesTable.id] ?: 0,
        category_name = this[ImageCategoriesTable.categoryName] ?: "",
        category_url = this[ImageCategoriesTable.category_url] ?: "",
        category_date = Date.valueOf(this[ImageCategoriesTable.categoryDate]).time.convertLongToDate(),
        category_blur_hash = this[ImageCategoriesTable.blur_hash] ?: ""
)