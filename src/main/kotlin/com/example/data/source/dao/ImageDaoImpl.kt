package com.example.data.source.dao

import com.example.data.dto.imageDetails.IdAndUrlImagesWithDto
import com.example.data.dto.imageDetails.ImageCategoryDto
import com.example.data.dto.imageDetails.ImageDetailsWithUserLikeAndPrimaryDto
import com.example.data.dto.imageDetails.ImageDetailsDto
import com.example.data.dto.tags.TagsDto
import com.example.data.source.queries.*
import com.example.data.tables.*
import com.example.domain.queryMapper.images.*
import com.example.domain.queryMapper.tags.tagsTableToTagsDtoMapper
import org.ktorm.database.Database
import org.ktorm.dsl.*

class ImageDaoImpl(
        private var dataBase: Database,
) : ImageDao {
    override suspend fun getAllCategoryImage(): List<ImageCategoryDto> {
        return dataBase.from(ImageCategoriesTable)
                .select().map { it.imageCategoryMapper() }
    }

    override suspend fun getLiteImagesByDate(pageSize: Int, page: Int): List<ImageDetailsDto> {
        return dataBase.getLiteImagesOrderByDate(
                pageSize = pageSize,
                page = page
        ).map { it.imageDetailsWithoutUserDetailsRowMapper() }
    }

    override suspend fun getTenTopRatedLiteImagesThreeWeeksAgo(limit: Int)
            : List<ImageDetailsDto> {
        return dataBase.getTopRatedLiteImagesThreeWeeksAgoQuery(limit = limit)
                .map { it.imageDetailsWithoutUserDetailsRowMapper() }
    }

    override suspend fun getTopRatedLiteImages(
            pageSize: Int,
            pageNumber: Int
    ): List<ImageDetailsDto> {
        return dataBase.listOfTopRatedLiteImages(
                pageSize = pageSize,
                pageNumber = pageNumber
        ).map { it.imageDetailsWithoutUserDetailsRowMapper() }

    }

    override suspend fun searchImagesByImageTitle(imageTitle: String): List<ImageDetailsDto> {
        return dataBase.searchImagesByImageTitleName(title = imageTitle)
    }

    override suspend fun getImageSearchResultByTagName(tagName: String): List<ImageDetailsDto> {
        return dataBase.getImageSearchResultByTagName(tagName = tagName)
                .map { it.imageDetailsWithoutUserDetailsRowMapper() }
    }

    override suspend fun getAllLiteImageByCategories(
            pageSize: Int,
            page: Int,
            categoryId: Int
    ): List<ImageDetailsWithUserLikeAndPrimaryDto> {
        return dataBase.getAllLiteImagesByCategoryQuery(
                pageSize = pageSize,
                page = page,
                categoryId = categoryId
        ).map { it.imageDetailsRowMapper() }
    }

    // this for make Blur Hash
    override suspend fun getAllLiteImage(): List<IdAndUrlImagesWithDto> {
        return dataBase.getIdAnUrlFromAllLiteImages().map { it.idAndUrlImageMapperRow() }
    }

    override suspend fun getAllLiteCategories(): List<ImageCategoryDto> {
        return dataBase.getAllImagesCategoriesLite().map { it.imageCategoryMapper() }
    }

    override suspend fun updateBlurHashForImageByImageId(imageId: Int, blurHash: String) {
        return dataBase.updateImageBlurHashByImageId(
                        imageId = imageId,
                        blurHash = blurHash
                )
    }

    override suspend fun updateCategoryBlurHashByCategoryId(categoryId: Int, blurHash: String) {
        return  dataBase.updateCategoryBlurHashByCategoryId(
                        categoryId = categoryId,
                        blurHash = blurHash
                )
    }

    override suspend fun getImagesDetailsBasedOnCategoryORColorId(
            categoryId: Int,
            userId: Int,
    ): List<ImageDetailsWithUserLikeAndPrimaryDto> {
        return dataBase.getImagesDetailsByCategoryIdQuery(
                        categoryID = categoryId,
                        userId = userId
                ).map { it.imageDetailsRowMapper() }
    }

    override suspend fun getImageDetailsBasedOnImagedId(imageId: Int, userId: Int):
            ImageDetailsWithUserLikeAndPrimaryDto {
        return dataBase.getImageDetailsByImageIdQuery(
                        imageId = imageId,
                        userId = userId
                ).map { it.imageDetailsRowMapper() }.first()
    }

    override suspend fun getImagesDetailsBasedOnRandomCategoryID(
            limit: Int,
            userId: Int
    ): List<ImageDetailsWithUserLikeAndPrimaryDto> {
        return dataBase.getImagesDetailsBasedOnRandomCategoryIdQuery(
                        limit = limit,
                        userId = userId
                ).map { it.imageDetailsRowMapper() }
    }

    override suspend fun getLastUserImagesLikes(pageSize: Int, pageNumber: Int, userId: Int)
            : List<ImageDetailsDto> {
        return dataBase.getLastUserImagesLikes(pageSize = pageSize, pageNumber = pageNumber, userId = userId)
            .map { it.imageDetailsWithoutUserDetailsRowMapper() }
    }

    override suspend fun getAllUserOwnedPrimaryImage(pageSize: Int, pageNumber: Int, userId: Int)
            : List<ImageDetailsWithUserLikeAndPrimaryDto> {
        return dataBase.getAllUserOwnedPrimaryImageQuery(pageSize = pageSize, pageNumber = pageNumber, userId = userId)
                .map { it.imageDetailsRowMapper() }
    }

    override suspend fun checkIfUserLikedImageUseCase(userId: Int, imageId: Int): Boolean {
        return dataBase.checkIfUserLikedImage(userId = userId, imageId = imageId).map {
                it[coalesce(
                        count(ImageUserLikesTable.user_id),
                        defaultValue = 0
                )
                        .aliased("like_count")]
            }.first() != 0
    }

    override suspend fun addUserLikeImageUseCase(userId: Int, imageId: Int): Boolean {
        return dataBase.addUserLikeImageQuery(userId = userId, imageId = imageId)!= 0
    }

    override suspend fun addPrimaryImageToUser(userId: Int, imageId: Int): Boolean {
        return dataBase.addPrimaryImageToUserQuery(userId = userId, imageId = imageId)!= 0

    }

    override suspend fun removeUserLikeImageUseCase(userId: Int, imageId: Int): Boolean {
        return  dataBase.removeUserLikeImage(userId = userId, imageId = imageId)!= 0
    }

    override suspend fun deleteImageFromUserCollection(collectionId: Int, imageId: Int): Boolean {
        return dataBase.deleteImageFromUserCollection(collectionId = collectionId, imageId = imageId) != 0
    }

    override suspend fun updateImageWatchCount(imageId: Int): Boolean {
        return dataBase.updateWatchImage(imageId = imageId)!= 0
    }

    override suspend fun updateLikedImageCountByIncrease(imageId: Int): Boolean {
        return dataBase.updateLikedImageCountByIncrease(imageId = imageId)!= 0
    }

    override suspend fun updateLikedImageCountByDecrease(imageId: Int): Boolean {
        return dataBase.updateLikedImageCountByDecrease(imageId = imageId)!= 0
    }

    override suspend fun getListOfImageTagsDependOnImageId(imageId: Int): List<TagsDto> {
        return dataBase.getAllImageTagsDependOnImageIdQuery(imageId = imageId).map { it.tagsTableToTagsDtoMapper() }
    }


    /**
     * Developer Mood :)
     */
    override suspend fun insertImageDetails(blurHash: String, likeCount: Int, categoryId: Int, imageTitle: String) {
        dataBase.insertImageDetails(blurHash = blurHash, likeCount = likeCount, categoryId = categoryId, imageTitle = imageTitle)
    }

    override suspend fun insertCollectionTitleImage(url: String, codeName: String, id: Int) {
        dataBase.insertImageCollectionTitleQuery(url = url, codeName = codeName, id = id)
    }

    override suspend fun insertSmallImage(url: String, codeName: String, id: Int) {
        dataBase.insertSmallImageQuery(url = url, codeName = codeName, id = id)
    }

    override suspend fun insertBackgroundImage(url: String, codeName: String, id: Int) {
        dataBase.insertBackgroundImageQuery(url = url, codeName = codeName, id = id)
    }

    override suspend fun updateImageDetails(imageTitle: String, url: String): Int {
        return dataBase.updateImageUrlBasedOnImageNameForDeveloperMoodQuery(url = url, name = imageTitle)
    }
}


/**
 *  Delete
 */
//    override suspend fun <T : Entity<T>> getCountOfTableItems(table: Table<T>): Int {
//        return dataBase.getCountOfTableItemsQuery(table = table)
//    }

//    override suspend fun getTotalPagesTable(columnName: Column<Int>, pageSize: Int): Int {
//        return dataBase.getTotalPagesTableQuery(columnName, pageSize)
//    }