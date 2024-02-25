package com.example.data.source.dao

import com.example.data.dto.imageDetails.*
import com.example.data.dto.tags.TagsDto

interface ImageDao {

//    suspend fun <T : Entity<T>> getCountOfTableItems(table: Table<T>): Int

//    suspend fun getTotalPagesTable(columnName: Column<Int>, pageSize: Int): Int

    suspend fun getAllCategoryImage(): List<ImageCategoryDto>

    suspend fun getLiteImagesByDate(pageSize: Int, page: Int): List<ImageDetailsDto>

    suspend fun getTenTopRatedLiteImagesThreeWeeksAgo(limit: Int): List<ImageDetailsDto>

    suspend fun getTopRatedLiteImages(pageSize: Int, pageNumber: Int): List<ImageDetailsDto>

    suspend fun searchImagesByImageTitle(imageTitle: String): List<ImageDetailsDto>
    suspend fun getImageSearchResultByTagName(tagName: String): List<ImageDetailsDto>

    suspend fun getAllLiteCategories(): List<ImageCategoryDto>

    suspend fun getAllLiteImageByCategories(
            pageSize: Int,
            page: Int,
            categoryId: Int
    ): List<ImageDetailsWithUserLikeAndPrimaryDto>

    suspend fun getAllLiteImage(): List<IdAndUrlImagesWithDto>


    /**
     *  For Admin In The Future
     */
    suspend fun updateBlurHashForImageByImageId(imageId: Int, blurHash: String)

    suspend fun updateCategoryBlurHashByCategoryId(categoryId: Int, blurHash: String)

    suspend fun getImagesDetailsBasedOnCategoryORColorId(
            categoryId: Int,
            userId: Int,
    ): List<ImageDetailsWithUserLikeAndPrimaryDto>

    suspend fun getImageDetailsBasedOnImagedId(imageId: Int, userId: Int): ImageDetailsWithUserLikeAndPrimaryDto

    suspend fun getImagesDetailsBasedOnRandomCategoryID(
            limit: Int,
            userId: Int,
    ): List<ImageDetailsWithUserLikeAndPrimaryDto>

    suspend fun getLastUserImagesLikes(pageSize: Int, pageNumber: Int, userId: Int):
            List<ImageDetailsDto>

    suspend fun getAllUserOwnedPrimaryImage(pageSize: Int, pageNumber: Int, userId: Int):
            List<ImageDetailsWithUserLikeAndPrimaryDto>

    suspend fun checkIfUserLikedImageUseCase(userId: Int, imageId: Int): Boolean
    suspend fun addUserLikeImageUseCase(userId: Int, imageId: Int): Boolean
    suspend fun addPrimaryImageToUser(userId: Int, imageId: Int): Boolean
    suspend fun removeUserLikeImageUseCase(userId: Int, imageId: Int): Boolean
    suspend fun deleteImageFromUserCollection(collectionId: Int, imageId: Int): Boolean
    suspend fun updateImageWatchCount(imageId: Int): Boolean
    suspend fun updateLikedImageCountByIncrease(imageId: Int): Boolean
    suspend fun updateLikedImageCountByDecrease(imageId: Int): Boolean
    suspend fun getListOfImageTagsDependOnImageId(imageId: Int): List<TagsDto>

    /**
     *  Developer Mood :)
     */
    suspend fun insertImageDetails(blurHash: String, likeCount: Int, categoryId: Int, imageTitle: String)
    suspend fun insertCollectionTitleImage(url: String, codeName: String, id: Int)
    suspend fun updateImageDetails(imageTitle: String, url: String): Int
    suspend fun insertBackgroundImage(url: String, codeName: String, id: Int)
    suspend fun insertSmallImage(url: String, codeName: String, id: Int)
}