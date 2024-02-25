package com.example.data.tables

import com.example.data.entities.ImageDetailsEntity
import org.ktorm.schema.*

object ImageDetailsTable : Table<ImageDetailsEntity>("image_details") {
    val id = int("id").bindTo { it.id }.primaryKey()
    val imgTitle = varchar("img_title").bindTo { it.imgTitle }
    val url = varchar("url").bindTo { it.url }
    val categoryId = int("category_id").references(ImageCategoriesTable) { it.categoryId }
    val blur_hash = varchar("blur_hash").bindTo { it.blurHash }
    val register = date("register").bindTo { it.register }
    val watchCount = int("watch_count").bindTo { it.watchCount }
    val likeCount = int("like_count").bindTo { it.likeCount }
    val imagePrice = int("image_price").bindTo { it.imagePrice }
    val imagePrimary = boolean("image_primary").bindTo { it.imagePrimary }
}