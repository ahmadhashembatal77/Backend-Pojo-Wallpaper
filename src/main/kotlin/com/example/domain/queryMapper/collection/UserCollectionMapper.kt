package com.example.domain.queryMapper.collection

import com.example.data.dto.collections.CollectionDetailsForCreateNewOne
import com.example.data.dto.collections.CollectionTitleImageDetailsDto
import com.example.data.dto.collections.CollectionWithUserDto
import com.example.data.tables.*
import org.ktorm.dsl.AssignmentsBuilder
import org.ktorm.dsl.QueryRowSet
import org.ktorm.dsl.eq
import org.ktorm.dsl.isNotNull
import org.ktorm.support.postgresql.defaultValue

fun QueryRowSet.rowToUserCollectionDto(userId: Int) = CollectionWithUserDto(
        collection_id = this[UserCollectionTable.id] ?: 0,
        collection_name = this[UserCollectionTable.collectionName] ?: "Empty",
        is_collection_visible = this[UserCollectionTable.isCollectionVisible] ?: false,
        collection_url = this.rowToCollectionTitleImageDetailsDto(),
        user_id = this[UserTable.userId] ?: 1,
        user_name = this[UserTable.userName] ?: "Empty",
        user_url = this[ProfileImageTable.url]?:"",
        likesCount = this[UserCollectionTable.likesCount] ?: 100,
        user_liked = this[UserCollectionsLikeTable.collection_id.isNotNull().aliased(label = "user_liked")] ?: false,
        image_number = this[UserCollectionTable.imageNumber] ?: 10,
        user_owned = this[UserTable.userId.eq(userId).aliased(label = "user_owned")] ?: false
)

fun QueryRowSet.rowToCollectionTitleImageDetailsDto() = CollectionTitleImageDetailsDto(
        collection_title_id = this[CollectionTitleImagesTable.id]?:0,
        collection_title_url = this[CollectionTitleImagesTable.url]?:"",
        collection_title_primary_image = this[CollectionTitleImagesTable.primaryImage]?:false,
        collection_title_image_price = this[CollectionTitleImagesTable.imagePrice]?:0,
        is_user_owned = this[UsersCollectionTitleImageTable.collectionTitleImageId
                .isNotNull().aliased(label = "is_user_owned")]?: false
)

fun AssignmentsBuilder.toCollectionEntityByBuilder(collection: CollectionDetailsForCreateNewOne){
    this.set(UserCollectionTable.id, UserCollectionTable.id.defaultValue())
    this.set(UserCollectionTable.userId, collection.user_id)
    this.set(UserCollectionTable.collectionName, collection.collection_name)
    this.set(UserCollectionTable.collectionUrl, 1)
    this.set(UserCollectionTable.isCollectionVisible, collection.collection_invisibility)
    this.set(UserCollectionTable.likesCount, 0)
    this.set(UserCollectionTable.imageNumber, 0)
}