package com.example.data.source.dao

import com.example.data.dto.collections.CollectionDetailsForCreateNewOne
import com.example.data.dto.collections.CollectionTitleImageDetailsDto
import com.example.data.dto.imageDetails.ImageDetailsWithUserLikeAndPrimaryDto
import com.example.data.dto.collections.CollectionWithUserDto
import com.example.data.dto.imageDetails.ImageDetailsDto
import org.ktorm.schema.Column

interface CollectionDao {
    suspend fun getUsersCollections(
        page: Int,
        pageSize: Int,
        userId: Int,
        invisibility: Boolean,
        minimumNumberOfImages: Int,
        maximumNumberOfImages: Int
    ): List<CollectionWithUserDto>

    suspend fun getImagesFromUsersCollectionsByCollectionId(collectionId: Int, userId: Int):
            List<ImageDetailsWithUserLikeAndPrimaryDto>
    suspend fun getImagesFromUsersCollectionsByCollectionIdWithoutUserDetails(collectionId: Int):
            List<ImageDetailsDto>
    suspend fun getUserCollectionDetails(collectionId: Int, userId: Int): CollectionWithUserDto
    suspend fun getAllUserCollectionsByUserId(userId: Int): List<CollectionWithUserDto>
    suspend fun getTotalPagesTable(columnName: Column<Int>, pageSize: Int): Int
    suspend fun addUserLikeToCollection(userId: Int, collectionId: Int): Boolean
    suspend fun removeUserLikeCollection(userId: Int, collectionId: Int): Boolean
    suspend fun checkIfUserLikedUserCollection(userId: Int, collectionId: Int): Boolean
    suspend fun updateLikedUserCollectionCountByIncrease(collectionId: Int): Boolean
    suspend fun updateLikedUserCollectionCountByDecrease(collectionId: Int): Boolean
    suspend fun getALlCollectionTitleImages(userId: Int, pageSize: Int, pageNumber: Int)
            : List<CollectionTitleImageDetailsDto>

    suspend fun updateUserCollectionTitleImage(collectionTitleId: Int, collectionId: Int): Int
    suspend fun updateUserCollectionTitleText(title: String, collectionId: Int): Int
    suspend fun updateImageNumberToUserCollection(collectionId: Int, isIncrease: Boolean): Int
    suspend fun addImageToCollectionForUserCollections(collectionId: Int, imageId: Int): Int
    suspend fun removeImageCollectionForUserCollection(collectionId: Int, imageId: Int): Int
    suspend fun createUserCollection(collection: CollectionDetailsForCreateNewOne): Int
    suspend fun checkIfImageAlreadyExistInTheCollection(collectionId: Int, imageId: Int): Boolean
    suspend fun updateCollectionVisibility(collectionId: Int, visibility: Boolean): Boolean
    suspend fun deleteUserCollection(collectionId: Int): Boolean
}