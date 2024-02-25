package com.example.domain.queryMapper.images

import com.example.data.dto.imageDetails.IdAndUrlImagesWithDto
import com.example.data.dto.imageDetails.ImageDetailsWithUserLikeAndPrimaryDto
import com.example.data.dto.imageDetails.ImageDetailsDto
import com.example.data.tables.*
import org.ktorm.dsl.QueryRowSet
import org.ktorm.dsl.isNotNull
import java.sql.ResultSet


fun QueryRowSet.imageDetailsRowMapper() = ImageDetailsWithUserLikeAndPrimaryDto(
        image_id = this[ImageDetailsTable.id] ?: 0,
        image_url = this[ImageDetailsTable.url] ?: "Empty",
        image_title = this[ImageDetailsTable.imgTitle] ?: "Empty",
        like_count = this[ImageDetailsTable.likeCount] ?: 0,
        watch_count = this[ImageDetailsTable.watchCount] ?: 0,
        blur_hash = this[ImageDetailsTable.blur_hash] ?: "Empty",
        image_price = this[ImageDetailsTable.imagePrice] ?: 0,
        image_primary = this[ImageDetailsTable.imagePrimary] ?: false,
        user_liked = this[ImageUserLikesTable.image_id.isNotNull().aliased("user_liked")] ?: false,
        user_owned_primary = this[UserOwnedPrimaryImageTable.image_id.isNotNull().aliased("user_owned_primary")] ?: false,
        category_id = this[ImageCategoriesTable.id]?:0
)
fun QueryRowSet.imageDetailsWithoutUserDetailsRowMapper() = ImageDetailsDto(
        image_id = this[ImageDetailsTable.id] ?: 0,
        image_url = this[ImageDetailsTable.url] ?: "Empty",
        image_title = this[ImageDetailsTable.imgTitle] ?: "Empty",
        like_count = this[ImageDetailsTable.likeCount] ?: 0,
        watch_count = this[ImageDetailsTable.watchCount] ?: 0,
        blur_hash = this[ImageDetailsTable.blur_hash] ?: "Empty",
        image_price = this[ImageDetailsTable.imagePrice] ?: 0,
        image_primary = this[ImageDetailsTable.imagePrimary] ?: false,
        category_id = this[ImageCategoriesTable.id]?:0
)
fun ResultSet.liteImageDetailsResultSet() = ImageDetailsDto(
        image_id = getInt("id"),
        image_url = getString("url") ?: "Empty",
        image_title = getString("img_title")?:"",
        blur_hash = getString("blur_hash") ?: "Empty",
        like_count = getInt("like_count"),
        category_id = getInt("id"),
        watch_count = getInt("watch_count"),
        image_price = getInt("image_price"),
        image_primary = getBoolean("image_primary")
)

fun QueryRowSet.idAndUrlImageMapperRow() = IdAndUrlImagesWithDto(
        image_id = this[ImageDetailsTable.id] ?: 0,
        image_url = this[ImageDetailsTable.url] ?: "Empty",
        blur_hash = this[ImageDetailsTable.blur_hash] ?: "Empty",
)