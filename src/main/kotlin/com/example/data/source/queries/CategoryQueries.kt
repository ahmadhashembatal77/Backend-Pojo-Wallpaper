package com.example.data.source.queries

import com.example.data.tables.ImageCategoriesTable
import org.ktorm.database.Database
import org.ktorm.dsl.*

fun Database.getAllImagesCategoriesLite(): Query {
    return this.from(ImageCategoriesTable).select(
        ImageCategoriesTable.id,
        ImageCategoriesTable.categoryName,
        ImageCategoriesTable.category_url,
        ImageCategoriesTable.blur_hash,
        ImageCategoriesTable.categoryDate
    ).orderBy(ImageCategoriesTable.id.asc())
}

fun Database.updateCategoryBlurHashByCategoryId(categoryId: Int, blurHash: String) {
    this.update(ImageCategoriesTable) { categoryTable ->
        set(ImageCategoriesTable.blur_hash, blurHash)
        where {
            categoryTable.id eq categoryId
        }
    }
}