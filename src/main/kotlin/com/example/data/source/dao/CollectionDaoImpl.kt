package com.example.data.source.dao

import com.example.data.dto.collections.CollectionDetailsForCreateNewOne
import com.example.data.dto.collections.CollectionTitleImageDetailsDto
import com.example.data.dto.imageDetails.ImageDetailsWithUserLikeAndPrimaryDto
import com.example.data.dto.collections.CollectionWithUserDto
import com.example.data.dto.imageDetails.ImageDetailsDto
import com.example.data.source.queries.*
import com.example.data.tables.UserCollectionsLikeTable
import com.example.data.tables.UserImageCollectionTable
import com.example.domain.queryMapper.collection.rowToCollectionTitleImageDetailsDto
import com.example.domain.queryMapper.collection.rowToUserCollectionDto
import com.example.domain.queryMapper.images.imageDetailsRowMapper
import com.example.domain.queryMapper.images.imageDetailsWithoutUserDetailsRowMapper
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.schema.Column

class CollectionDaoImpl(
    private var dataBase: Database,
) : CollectionDao {
    override suspend fun getUsersCollections(
        page: Int,
        pageSize: Int,
        userId: Int,
        invisibility: Boolean,
        minimumNumberOfImages: Int,
        maximumNumberOfImages: Int
    ): List<CollectionWithUserDto> {
        return dataBase.getUsersCollections(
                    page = page,
                    pageSize = pageSize,
                    userId = userId,
                    invisibility = invisibility,
                    minimumNumberOfImages = minimumNumberOfImages,
                    maximumNumberOfImages = maximumNumberOfImages
                ).map { it.rowToUserCollectionDto(userId = userId) }
    }

    override suspend fun getImagesFromUsersCollectionsByCollectionId(collectionId: Int, userId: Int):
            List<ImageDetailsWithUserLikeAndPrimaryDto> {
        return dataBase.getAllImageUserCollectionsByCollectionIdQuery(
                    collectionId = collectionId,
                    userId = userId
                ).map { it.imageDetailsRowMapper() }
    }

    override suspend fun getImagesFromUsersCollectionsByCollectionIdWithoutUserDetails(collectionId: Int)
            : List<ImageDetailsDto> {
        return dataBase.getAllImageUserCollectionsByCollectionIdWithoutUserIdQuery(collectionId = collectionId)
                .map { it.imageDetailsWithoutUserDetailsRowMapper() }
    }

    override suspend fun getUserCollectionDetails(collectionId: Int, userId: Int): CollectionWithUserDto {
        return dataBase.getUserCollectionDetails(collectionId = collectionId, userId = userId)
            .map { it.rowToUserCollectionDto(userId = userId) }.first()
    }

    override suspend fun getAllUserCollectionsByUserId(userId: Int): List<CollectionWithUserDto> {
        return dataBase.getAllUserCollectionsByUserId(userId = userId).map { it.rowToUserCollectionDto(userId = userId) }
    }

    override suspend fun getTotalPagesTable(columnName: Column<Int>, pageSize: Int): Int {
        return dataBase.getTotalPagesTableQuery(columnName, pageSize)
    }

    override suspend fun addUserLikeToCollection(userId: Int, collectionId: Int): Boolean {
        return dataBase.addUserLikeToCollection(userId = userId, collectionId = collectionId)!= 0
    }

    override suspend fun removeUserLikeCollection(userId: Int, collectionId: Int): Boolean {
        return dataBase.removeUserLikeToCollection(userId = userId, collectionId = collectionId)!= 0
    }

    override suspend fun checkIfUserLikedUserCollection(userId: Int, collectionId: Int): Boolean {
        return dataBase.checkIfUserLikedUserCollectionQuery(userId = userId, collectionId = collectionId).map {
                it[coalesce(
                    count(UserCollectionsLikeTable.user_id),
                    defaultValue = 0
                ).aliased("like_count")]
            }.first() != 0
    }

    override suspend fun updateLikedUserCollectionCountByIncrease(collectionId: Int): Boolean {
        return dataBase.updateLikedUserCollectionCountByIncreaseQuery(collectionId = collectionId)!= 0
    }

    override suspend fun updateLikedUserCollectionCountByDecrease(collectionId: Int): Boolean {
        return dataBase.updateLikedUserCollectionCountByDecreaseQuery(collectionId = collectionId)!= 0
    }

    override suspend fun getALlCollectionTitleImages(userId: Int, pageSize: Int, pageNumber: Int)
            : List<CollectionTitleImageDetailsDto> {
        return dataBase.getAllImageTitleCollectionsQuery(userId, pageSize = pageSize, pageNumber = pageNumber)
            .map { it.rowToCollectionTitleImageDetailsDto() }
    }

    override suspend fun updateUserCollectionTitleImage(collectionTitleId: Int, collectionId: Int): Int {
        return dataBase.updateUserCollectionTitleImageQuery(
            collectionTitleId = collectionTitleId,
            collectionId = collectionId
        )
    }

    override suspend fun updateUserCollectionTitleText(title: String, collectionId: Int): Int {
        return dataBase.updateUserCollectionTitleTextQuery(
            title = title,
            collectionId = collectionId
        )
    }

    override suspend fun updateImageNumberToUserCollection(collectionId: Int, isIncrease: Boolean): Int {
        return dataBase.updateImageNumberToUserCollectionQuery(
            collectionId = collectionId,
            isIncrease = isIncrease
        )
    }

    override suspend fun addImageToCollectionForUserCollections(collectionId: Int, imageId: Int): Int {
        return dataBase.addImageToCollectionForUserCollectionsQuery(
            imageId = imageId,
            collectionId = collectionId
        )
    }

    override suspend fun removeImageCollectionForUserCollection(collectionId: Int, imageId: Int): Int {
        return dataBase.removeImageCollectionForUserCollectionQuery(
            imageId = imageId,
            collectionId = collectionId
        )
    }

    override suspend fun createUserCollection(collection: CollectionDetailsForCreateNewOne): Int {
        return dataBase.createUserCollectionQuery(
            collection = collection,
        )
    }

    override suspend fun checkIfImageAlreadyExistInTheCollection(collectionId: Int, imageId: Int): Boolean {
        return dataBase.from(UserImageCollectionTable).select(UserImageCollectionTable.image_id).where {
            UserImageCollectionTable.collection_id.eq(collectionId) and
                    UserImageCollectionTable.image_id.eq(imageId)
        }.map { it[UserImageCollectionTable.image_id] ?: 0 }.toList().isNotEmpty()
    }

    override suspend fun updateCollectionVisibility(collectionId: Int, visibility: Boolean): Boolean {
        return dataBase.updateCollectionVisibility(collectionId = collectionId, visibility = visibility) == 1
    }

    override suspend fun deleteUserCollection(collectionId: Int): Boolean {
        return dataBase.deleteUserCollectionQuery(collectionId = collectionId)
    }
}