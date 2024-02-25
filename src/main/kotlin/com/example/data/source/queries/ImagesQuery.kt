package com.example.data.source.queries

import com.example.data.dto.imageDetails.ImageDetailsDto
import com.example.data.setMapper.*
import com.example.data.setMapper.toSmallImageEntityByBuilder
import com.example.data.tables.*
import com.example.domain.queryMapper.images.liteImageDetailsResultSet
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.expression.ArgumentExpression
import org.ktorm.expression.*
import org.ktorm.schema.ColumnDeclaring
import java.time.LocalDate

fun Database.getTopRatedLiteImagesThreeWeeksAgoQuery(limit: Int): Query {
    return this.from(ImageDetailsTable)
            .innerJoin(
                    right = ImageCategoriesTable,
                    on = ImageDetailsTable.categoryId.eq(ImageCategoriesTable.id)
            )
            .selectDistinct(
                    ImageDetailsTable.id,
                    ImageDetailsTable.url,
                    ImageDetailsTable.imgTitle,
                    ImageDetailsTable.blur_hash,
                    ImageDetailsTable.watchCount,
                    ImageDetailsTable.likeCount,
                    ImageDetailsTable.imagePrice,
                    ImageDetailsTable.imagePrimary,
                    ImageDetailsTable.register,
                    ImageCategoriesTable.id
            )
            .where { ImageDetailsTable.register.greaterEq(LocalDate.now().minusWeeks(3)) }
            .groupBy(
                    ImageDetailsTable.id,
                    ImageCategoriesTable.id
            )
            .limit(n = limit)
            .orderBy(ImageDetailsTable.likeCount.desc())
}

fun Database.listOfTopRatedLiteImages(
        pageSize: Int,
        pageNumber: Int
): Query {
    return this.from(ImageDetailsTable)
            .innerJoin(
                    right = ImageCategoriesTable,
                    on = ImageDetailsTable.categoryId.eq(ImageCategoriesTable.id)
            )
            .select(
                    ImageDetailsTable.id,
                    ImageDetailsTable.url,
                    ImageDetailsTable.imgTitle,
                    ImageDetailsTable.blur_hash,
                    ImageDetailsTable.watchCount,
                    ImageDetailsTable.likeCount,
                    ImageDetailsTable.imagePrice,
                    ImageDetailsTable.imagePrimary,
                    ImageDetailsTable.register,
                    ImageCategoriesTable.id
            )
            .groupBy(
                    ImageDetailsTable.id,
                    ImageCategoriesTable.id
            )
            .orderBy(ImageDetailsTable.likeCount.desc())
            .limit(pageSize)
            .offset((pageNumber - 1) * pageSize)
}

fun Database.getLiteImagesOrderByDate(pageSize: Int, page: Int): Query {
    return this.from(ImageDetailsTable).innerJoin(
            right = ImageCategoriesTable,
            on = ImageDetailsTable.categoryId.eq(ImageCategoriesTable.id)
    ).selectDistinct(
            ImageDetailsTable.id,
            ImageDetailsTable.url,
            ImageDetailsTable.imgTitle,
            ImageDetailsTable.blur_hash,
            ImageDetailsTable.watchCount,
            ImageDetailsTable.likeCount,
            ImageDetailsTable.imagePrice,
            ImageDetailsTable.imagePrimary,
            ImageDetailsTable.register,
            ImageCategoriesTable.id,
    )
            .limit(pageSize)
            .offset((page - 1) * pageSize)
            .groupBy(
                    ImageDetailsTable.id,
                    ImageCategoriesTable.id
            ).orderBy(ImageDetailsTable.register.desc())
}

fun Database.getAllLiteImagesByCategoryQuery(
        pageSize: Int,
        page: Int,
        categoryId: Int
): Query {
    return this.from(ImageDetailsTable)
            .innerJoin(
                    right = ImageCategoriesTable,
                    on = ImageDetailsTable.categoryId.eq(ImageCategoriesTable.id)
            )
//        .leftJoin(
//            right = ImagesTagsTable,
//            on = ImageDetailsTable.id.eq(ImagesTagsTable.image_id)
//        ).leftJoin(
//            right = TagsTable,
//            on = ImagesTagsTable.tag_id.eq(TagsTable.id)
//        )
            .select(
                    ImageDetailsTable.id,
                    ImageDetailsTable.url,
                    ImageDetailsTable.imgTitle,
                    ImageDetailsTable.blur_hash,
                    ImageDetailsTable.watchCount,
                    ImageDetailsTable.likeCount,
                    ImageDetailsTable.imagePrice,
                    ImageDetailsTable.imagePrimary,
                    ImageDetailsTable.register,
                    ImageCategoriesTable.id
            )
            .where {
//            TagsTable.tag_name.like("%$categoryName%") or
                ImageCategoriesTable.id.eq(categoryId)
            }
            .limit(pageSize)
            .offset((page - 1) * pageSize)
            .groupBy(
                    ImageDetailsTable.id,
                    ImageCategoriesTable.id,
            ).orderBy(ImageDetailsTable.register.desc())
}

fun Database.getIdAnUrlFromAllLiteImages(): Query {
    return this.from(ImageDetailsTable)
            .select(
                    ImageDetailsTable.id,
                    ImageDetailsTable.url,
                    ImageDetailsTable.blur_hash
            )
            .orderBy(ImageDetailsTable.id.desc())
}

fun Database.getImageDetailsByImageIdQuery(imageId: Int, userId: Int): Query {
    return this.from(ImageDetailsTable)
            .innerJoin(
                    right = ImageCategoriesTable,
                    on = ImageDetailsTable.categoryId eq ImageCategoriesTable.id
            ).leftJoin(
                    right = ImageUserLikesTable,
                    on = ImageDetailsTable.id.eq(ImageUserLikesTable.image_id) and
                            (ImageUserLikesTable.user_id.eq(userId))
            ).leftJoin(
                    right = UserOwnedPrimaryImageTable,
                    on = ImageDetailsTable.id.eq(UserOwnedPrimaryImageTable.image_id) and
                            (UserOwnedPrimaryImageTable.user_id.eq(userId))
            ).select(
                    ImageDetailsTable.id,
                    ImageDetailsTable.url,
                    ImageDetailsTable.imgTitle,
                    ImageDetailsTable.blur_hash,
                    ImageDetailsTable.watchCount,
                    ImageDetailsTable.likeCount,
                    ImageDetailsTable.imagePrice,
                    ImageDetailsTable.imagePrimary,
                    ImageUserLikesTable.image_id.isNotNull().aliased("user_liked"),
                    UserOwnedPrimaryImageTable.image_id.isNotNull().aliased("user_owned_primary"),
                    ImageDetailsTable.register,
                    ImageCategoriesTable.id
            ).limit(1)
            .where {
                ImageDetailsTable.id.eq(imageId)
            }.groupBy(
                    ImageCategoriesTable.id,
                    ImageDetailsTable.id,
                    ImageUserLikesTable.user_id,
                    ImageUserLikesTable.image_id,
                    UserOwnedPrimaryImageTable.image_id
            )
}

fun Database.getImagesDetailsByCategoryIdQuery(
        categoryID: Int,
        userId: Int
): Query {
    return this.from(ImageDetailsTable)
            .innerJoin(
                    right = ImageCategoriesTable,
                    on = ImageDetailsTable.categoryId.eq(ImageCategoriesTable.id)
            ).leftJoin(
                    right = ImageUserLikesTable,
                    on = ImageDetailsTable.id.eq(ImageUserLikesTable.image_id) and
                            (ImageUserLikesTable.user_id.eq(userId))
            ).leftJoin(
                    right = UserOwnedPrimaryImageTable,
                    on = ImageDetailsTable.id.eq(UserOwnedPrimaryImageTable.image_id) and
                            (UserOwnedPrimaryImageTable.user_id.eq(userId))
            ).selectDistinct(
                    ImageDetailsTable.id,
                    ImageDetailsTable.url,
                    ImageDetailsTable.imgTitle,
                    ImageDetailsTable.blur_hash,
                    ImageDetailsTable.watchCount,
                    ImageDetailsTable.likeCount,
                    ImageDetailsTable.imagePrice,
                    ImageDetailsTable.imagePrimary,
                    ImageUserLikesTable.image_id.isNotNull().aliased("user_liked"),
                    UserOwnedPrimaryImageTable.image_id.isNotNull().aliased("user_owned_primary"),
                    ImageDetailsTable.register,
                    ImageCategoriesTable.id
            )
            .where {
                ImageCategoriesTable.id.eq(categoryID)
            }
            .groupBy(
                    ImageCategoriesTable.id,
                    ImageDetailsTable.id,
                    ImageUserLikesTable.user_id,
                    ImageUserLikesTable.image_id,
                    UserOwnedPrimaryImageTable.image_id
            ).limit(n = 59)
}

fun Database.getImagesDetailsBasedOnRandomCategoryIdQuery(limit: Int, userId: Int): Query {
    val randomCategoryId = (1..10).shuffled().random()
    return this.from(ImageDetailsTable)
            .innerJoin(
                    right = ImageCategoriesTable,
                    on = ImageDetailsTable.categoryId.eq(ImageCategoriesTable.id)
            )
            .leftJoin(
                    right = ImageUserLikesTable,
                    on = ImageDetailsTable.id.eq(ImageUserLikesTable.image_id) and
                            (ImageUserLikesTable.user_id.eq(userId))
            ).leftJoin(
                    right = UserOwnedPrimaryImageTable,
                    on = ImageDetailsTable.id.eq(UserOwnedPrimaryImageTable.image_id) and
                            (UserOwnedPrimaryImageTable.user_id.eq(userId))
            ).selectDistinct(
                    ImageDetailsTable.id,
                    ImageDetailsTable.url,
                    ImageDetailsTable.imgTitle,
                    ImageDetailsTable.blur_hash,
                    ImageDetailsTable.watchCount,
                    ImageDetailsTable.likeCount,
                    ImageDetailsTable.imagePrice,
                    ImageDetailsTable.imagePrimary,
                    ImageUserLikesTable.image_id.isNotNull().aliased("user_liked"),
                    UserOwnedPrimaryImageTable.image_id.isNotNull().aliased("user_owned_primary"),
                    ImageDetailsTable.register,
                    ImageCategoriesTable.id,
            )
            .where {
                ImageCategoriesTable.id.eq(randomCategoryId)
            }
            .limit(limit)
            .orderBy(
                    ImageDetailsTable.id.desc()
            )
            .groupBy(
                    ImageCategoriesTable.id,
                    ImageDetailsTable.id,
                    ImageUserLikesTable.image_id,
                    UserOwnedPrimaryImageTable.image_id
            )
}

fun Database.searchImagesByImageTitleName(title: String): List<ImageDetailsDto> {
    val qResult = mutableListOf<ImageDetailsDto>()
    this.useConnection { conn ->
        val queryResult = conn.prepareStatement(
                """
            SELECT
                image_details.id, 
                image_details.img_title,
                image_details.url, 
                image_details.blur_hash,
                image_details.register,
                image_details.watch_count,
                image_details.like_count,
                image_details.image_price,
                image_details.image_primary,
                img_categories.id
            FROM
                image_details
            INNER JOIN 
                img_categories ON image_details.category_id = img_categories.id
            WHERE 
                to_tsvector('english', img_title) @@ to_tsquery('english', '$title')
            ORDER BY image_details.register DESC
            LIMIT 200
        """
        ).executeQuery()
        while (queryResult.next()) {
            qResult.add(queryResult.liteImageDetailsResultSet())
        }
        return qResult.toList()
    }
}

fun Database.getImageSearchResultByTagName(tagName: String): Query {
    return this.from(ImageDetailsTable)
            .innerJoin(
                    right = ImageCategoriesTable,
                    on = ImageDetailsTable.categoryId.eq(ImageCategoriesTable.id)
            ).innerJoin(
                    right = ImagesTagsTable,
                    on = ImageDetailsTable.id.eq(ImagesTagsTable.image_id)
            ).innerJoin(
                    right = TagsTable,
                    on = TagsTable.id.eq(ImagesTagsTable.tag_id)
            ).selectDistinct(
                    ImageDetailsTable.id,
                    ImageDetailsTable.url,
                    ImageDetailsTable.imgTitle,
                    ImageDetailsTable.blur_hash,
                    ImageDetailsTable.watchCount,
                    ImageDetailsTable.likeCount,
                    ImageDetailsTable.imagePrice,
                    ImageDetailsTable.imagePrimary,
                    ImageDetailsTable.register,
                    ImageCategoriesTable.id
            ).where {
                TagsTable.tag_name.like("%$tagName%")
            }.orderBy(
                    ImageDetailsTable.register.desc()
            ).limit(n = 200)
}

fun Database.getLastUserImagesLikes(pageSize: Int, pageNumber: Int, userId: Int): Query {
    return this.from(ImageDetailsTable)
            .innerJoin(
                    right = ImageCategoriesTable,
                    on = ImageDetailsTable.categoryId.eq(ImageCategoriesTable.id)
            )
            .leftJoin(
                    right = ImageUserLikesTable,
                    on = ImageDetailsTable.id.eq(ImageUserLikesTable.image_id) and
                            (ImageUserLikesTable.user_id.eq(userId))
            )
            .select(
                    ImageDetailsTable.id,
                    ImageDetailsTable.url,
                    ImageDetailsTable.imgTitle,
                    ImageDetailsTable.blur_hash,
                    ImageDetailsTable.watchCount,
                    ImageDetailsTable.likeCount,
                    ImageDetailsTable.imagePrice,
                    ImageDetailsTable.imagePrimary,
                    ImageDetailsTable.register,
                    ImageCategoriesTable.id,
                    ImageUserLikesTable.user_like_register
            )
            .groupBy(
                    ImageDetailsTable.id,
                    ImageCategoriesTable.id,
                    ImageUserLikesTable.user_id,
                    ImageUserLikesTable.image_id,
            )
            .where { ImageUserLikesTable.user_id.eq(userId) }
            .orderBy(ImageUserLikesTable.user_like_register.desc())
            .limit(pageSize)
            .offset((pageNumber - 1) * pageSize)
}

fun Database.getAllUserOwnedPrimaryImageQuery(pageSize: Int, pageNumber: Int, userId: Int): Query {
    return this.from(ImageDetailsTable)
            .innerJoin(
                    right = ImageCategoriesTable,
                    on = ImageDetailsTable.categoryId.eq(ImageCategoriesTable.id)
            )
            .innerJoin(
                    right = UserOwnedPrimaryImageTable,
                    on = ImageDetailsTable.id.eq(UserOwnedPrimaryImageTable.image_id) and
                            (UserOwnedPrimaryImageTable.user_id.eq(userId))
            )
            .leftJoin(
                    right = ImageUserLikesTable,
                    on = ImageDetailsTable.id.eq(ImageUserLikesTable.image_id) and
                            (ImageUserLikesTable.user_id.eq(userId))
            )
            .select(
                    ImageDetailsTable.id,
                    ImageDetailsTable.url,
                    ImageDetailsTable.imgTitle,
                    ImageDetailsTable.blur_hash,
                    ImageDetailsTable.watchCount,
                    ImageDetailsTable.likeCount,
                    ImageDetailsTable.imagePrice,
                    ImageDetailsTable.imagePrimary,
                    ImageUserLikesTable.image_id.isNotNull().aliased("user_liked"),
                    UserOwnedPrimaryImageTable.image_id.isNotNull().aliased("user_owned_primary"),
                    ImageDetailsTable.register,
                    ImageCategoriesTable.id,
                    UserOwnedPrimaryImageTable.user_owned_time
            )
            .groupBy(
                    ImageDetailsTable.id,
                    ImageCategoriesTable.id,
                    ImageUserLikesTable.user_id,
                    ImageUserLikesTable.image_id,
                    UserOwnedPrimaryImageTable.image_id,
                    UserOwnedPrimaryImageTable.user_owned_time
            )
            .orderBy(
                    UserOwnedPrimaryImageTable.user_owned_time.desc()
            )
            .limit(pageSize)
            .offset((pageNumber - 1) * pageSize)
}

/*
* SELECT tags.*
FROM tags
JOIN image_tags_table ON tags.tag_id = image_tags_table.tag_id
WHERE image_tags_table.image_id = <your_image_id>;
* */

fun Database.getAllImageTagsDependOnImageIdQuery(imageId: Int): Query {
    return from(TagsTable)
            .innerJoin(
                    right = ImagesTagsTable,
                    on = TagsTable.id.eq(ImagesTagsTable.tag_id)
            )
            .select(
                    TagsTable.id,
                    TagsTable.tag_name,
            ).where {
                ImagesTagsTable.image_id.eq(imageId)
            }.orderBy(
                    TagsTable.id.desc()
            ).groupBy(
                    ImagesTagsTable.image_id,
                    TagsTable.id
            )
}

fun Database.addUserLikeImageQuery(userId: Int, imageId: Int): Int {
    return this.insert(ImageUserLikesTable) {
        this.set(ImageUserLikesTable.user_id, userId)
        this.set(ImageUserLikesTable.image_id, imageId)
    }
}

fun Database.addPrimaryImageToUserQuery(userId: Int, imageId: Int): Int {
    return this.insert(UserOwnedPrimaryImageTable) {
        this.set(UserOwnedPrimaryImageTable.user_id, userId)
        this.set(UserOwnedPrimaryImageTable.image_id, imageId)
    }
}

fun Database.removeUserLikeImage(userId: Int, imageId: Int): Int {
    return this.delete(ImageUserLikesTable) {
        it.user_id.eq(userId) and it.image_id.eq(imageId)
    }
}

fun Database.deleteImageFromUserCollection(collectionId: Int, imageId: Int): Int {
    return this.delete(UserImageCollectionTable) {
        it.collection_id.eq(collectionId) and it.image_id.eq(imageId)
    }
}

fun Database.checkIfUserLikedImage(userId: Int, imageId: Int): Query {
    return this.from(ImageUserLikesTable)
            .select(
                    coalesce(
                            count(
                                    ImageUserLikesTable.user_id
                            ),
                            defaultValue = 0
                    ).aliased("like_count")
            ).where {
                ImageUserLikesTable.user_id.eq(userId) and
                        ImageUserLikesTable.image_id.eq(imageId)
            }
}

fun Database.updateWatchImage(imageId: Int): Int {
    return this.update(ImageDetailsTable) {
        set(ImageDetailsTable.watchCount, ImageDetailsTable.watchCount + 1)
        this.where { ImageDetailsTable.id.eq(imageId) }
    }
}

fun Database.updateLikedImageCountByIncrease(imageId: Int): Int {
    return this.update(ImageDetailsTable) {
        set(ImageDetailsTable.likeCount, ImageDetailsTable.likeCount + 1)
        this.where { ImageDetailsTable.id.eq(imageId) }
    }
}

fun Database.updateLikedImageCountByDecrease(imageId: Int): Int {
    return this.update(ImageDetailsTable) {
        set(ImageDetailsTable.likeCount, ImageDetailsTable.likeCount - 1)
        this.where { ImageDetailsTable.id.eq(imageId) }
    }
}

/**
 *  If Their No Value Set Default Value
 */
fun <T : Any> coalesce(column: ColumnDeclaring<T>, defaultValue: T): FunctionExpression<T> {
    return FunctionExpression(
            functionName = "coalesce",
            arguments = listOf(column.asExpression(), ArgumentExpression(defaultValue, column.sqlType)),
            sqlType = column.sqlType
    )
}

/**
 * For Admin In Future
 */
fun Database.updateImageBlurHashByImageId(imageId: Int, blurHash: String) {
    this.update(ImageDetailsTable) { imageTable ->
        set(imageTable.blur_hash, blurHash)
        where { imageTable.id eq imageId }
    }
}

fun Database.insertImageDetails(blurHash: String, likeCount: Int, categoryId: Int, imageTitle: String) {
    this.insert(ImageDetailsTable) { _ ->
        this.toImageEntityByBuilder(categoryId = categoryId, blurHash = blurHash,
                likeCount = likeCount,
                imageTitle = imageTitle
        )
    }
}

fun Database.insertImageCollectionTitleQuery(url: String, codeName: String, id: Int) {
    this.insert(CollectionTitleImagesTable) { _ ->
        this.toCollectionTitleImageEntityByBuilder(
                url = url,
                price = 0,
                codeName = codeName,
                id = id
        )
    }
}

fun Database.insertSmallImageQuery(url: String, codeName: String, id: Int) {
    this.insert(ProfileImageTable) { _ ->
        this.toSmallImageEntityByBuilder(
                url = url,
                price = 0,
                codeName = codeName,
                id = id
        )
    }
}

fun Database.insertBackgroundImageQuery(url: String, codeName: String, id: Int) {
    this.insert(UserBackgroundImageTable) { _ ->
        this.toBackgroundImageEntityByBuilder(
                url = url,
                price = 0,
                codeName = codeName,
                id = id
        )
    }
}

fun Database.updateImageUrlBasedOnImageNameForDeveloperMoodQuery(url: String, name: String): Int {
    return this.update(ImageDetailsTable) { imageTable ->
        set(imageTable.url, url)
        where { imageTable.imgTitle eq name }
    }
}