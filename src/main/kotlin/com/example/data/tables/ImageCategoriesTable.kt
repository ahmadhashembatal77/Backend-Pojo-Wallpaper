package com.example.data.tables

import com.example.data.entities.ImageCategories

import org.ktorm.schema.Table
import org.ktorm.schema.date
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object ImageCategoriesTable : Table<ImageCategories>("img_categories") {
    val id = int("id").bindTo { it.id }.primaryKey()
    val categoryName = varchar("category_name").bindTo { it.categoryName }
    val category_url = varchar("category_url").bindTo { it.categoryUrl }
    val blur_hash = varchar("blur_hash").bindTo { it.blurHash }
    val categoryDate = date("category_date").bindTo { it.categoryDate }
}