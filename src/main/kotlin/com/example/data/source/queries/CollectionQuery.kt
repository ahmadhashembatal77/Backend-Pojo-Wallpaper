package com.example.data.source.queries

import com.example.data.dto.collections.CollectionDetailsForCreateNewOne
import com.example.data.tables.*
import com.example.domain.queryMapper.collection.toCollectionEntityByBuilder
import org.ktorm.database.Database
import org.ktorm.dsl.*

fun Database.getUsersCollections(
        page: Int,
        pageSize: Int,
        userId: Int,
        invisibility: Boolean,
        minimumNumberOfImages: Int,
        maximumNumberOfImages: Int
): Query {
    return this.from(UserCollectionTable)
            .innerJoin(
                    right = UserTable,
                    on = UserTable.userId.eq(UserCollectionTable.userId)
            ).innerJoin(
                    right = CollectionTitleImagesTable,
                    on = CollectionTitleImagesTable.id.eq(UserCollectionTable.collectionUrl)
            ).innerJoin(
                    right = ProfileImageTable,
                    on = ProfileImageTable.id.eq(UserTable.profileImage)
            )
            .leftJoin(
                    right = UserCollectionsLikeTable,
                    on = UserCollectionTable.id.eq(UserCollectionsLikeTable.collection_id) and
                            UserCollectionsLikeTable.user_id.eq(userId)
            )
            .select(
                    UserCollectionTable.id,
                    UserCollectionTable.collectionName,
                    UserCollectionTable.collectionUrl,
                    UserCollectionTable.isCollectionVisible,
                    UserCollectionTable.likesCount,
                    UserCollectionTable.imageNumber,
                    CollectionTitleImagesTable.id,
                    CollectionTitleImagesTable.url,
                    CollectionTitleImagesTable.imagePrice,
                    CollectionTitleImagesTable.primaryImage,
                    UserTable.userId,
                    UserTable.userName,
                    ProfileImageTable.url,
                    UserCollectionsLikeTable.collection_id.isNotNull().aliased("user_liked"),
                    UserTable.userId.eq(userId).aliased("user_owned")
            ).where {
                UserCollectionTable.isCollectionVisible.eq(value = invisibility) and
                        UserCollectionTable.imageNumber.between(range = minimumNumberOfImages..maximumNumberOfImages)
            }.limit(pageSize)
            .offset((page - 1) * pageSize)
            .orderBy(
                    UserCollectionTable.likesCount.desc(),
                    UserCollectionTable.id.desc()
            )
            .groupBy(
                    CollectionTitleImagesTable.id,
                    ProfileImageTable.id,
                    UserCollectionsLikeTable.collection_id,
                    UserCollectionTable.id,
                    UserTable.userId
            )
}

fun Database.getAllImageUserCollectionsByCollectionIdQuery(collectionId: Int, userId: Int): Query {
    return this.from(ImageDetailsTable)
            .innerJoin(
                    right = ImageCategoriesTable,
                    on = ImageDetailsTable.categoryId.eq(ImageCategoriesTable.id)
            )
            .innerJoin(
                    right = UserImageCollectionTable,
                    on = ImageDetailsTable.id.eq(UserImageCollectionTable.image_id)
            ).innerJoin(
                    right = UserCollectionTable,
                    on = UserCollectionTable.id.eq(UserImageCollectionTable.collection_id)
            ).innerJoin(
                    right = UserTable,
                    on = UserTable.userId.eq(UserCollectionTable.userId)
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
                    ImageCategoriesTable.id,
            ).where {
                UserCollectionTable.id.eq(collectionId)
            }.groupBy(
                    ImageDetailsTable.id,
                    ImageCategoriesTable.id,
                    UserTable.userId,
                    ImageUserLikesTable.image_id,
                    ImageUserLikesTable.user_id,
                    UserOwnedPrimaryImageTable.image_id
            ).orderBy(ImageDetailsTable.id.desc())
}

fun Database.getAllImageUserCollectionsByCollectionIdWithoutUserIdQuery(collectionId: Int): Query {
    return this.from(ImageDetailsTable)
            .innerJoin(
                    right = ImageCategoriesTable,
                    on = ImageDetailsTable.categoryId.eq(ImageCategoriesTable.id)
            )
            .innerJoin(
                    right = UserImageCollectionTable,
                    on = ImageDetailsTable.id.eq(UserImageCollectionTable.image_id)
            ).innerJoin(
                    right = UserCollectionTable,
                    on = UserCollectionTable.id.eq(UserImageCollectionTable.collection_id)
            ).select(
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
            ).where {
                UserCollectionTable.id.eq(collectionId)
            }.groupBy(
                    ImageDetailsTable.id,
                    ImageCategoriesTable.id,
            ).orderBy(ImageDetailsTable.id.desc())
}

fun Database.getUserCollectionDetails(collectionId: Int, userId: Int): Query {
    return this.from(UserCollectionTable)
            .innerJoin(
                    right = UserTable,
                    on = UserTable.userId.eq(UserCollectionTable.userId)
            ).innerJoin(
                    right = CollectionTitleImagesTable,
                    on = CollectionTitleImagesTable.id.eq(UserCollectionTable.collectionUrl)
            )
            .innerJoin(
                    right = ProfileImageTable,
                    on = ProfileImageTable.id.eq(UserTable.profileImage)
            )
            .leftJoin(
                    right = UserCollectionsLikeTable,
                    on = UserCollectionTable.id.eq(UserCollectionsLikeTable.collection_id) and
                            UserCollectionsLikeTable.user_id.eq(userId)
            )
            .select(
                    UserCollectionTable.id,
                    UserCollectionTable.collectionName,
                    UserCollectionTable.isCollectionVisible,
                    UserCollectionTable.imageNumber,
                    CollectionTitleImagesTable.id,
                    CollectionTitleImagesTable.url,
                    CollectionTitleImagesTable.imagePrice,
                    CollectionTitleImagesTable.primaryImage,
                    UserTable.userId,
                    UserTable.userName,
                    ProfileImageTable.url,
                    UserCollectionTable.likesCount,
                    UserCollectionsLikeTable.collection_id.isNotNull().aliased("user_liked"),
                    UserTable.userId.eq(userId).aliased("user_owned")
            ).where {
                UserCollectionTable.id.eq(collectionId)
            }
            .groupBy(
                    CollectionTitleImagesTable.id,
                    ProfileImageTable.id,
                    UserCollectionsLikeTable.collection_id,
                    UserCollectionTable.id,
                    UserTable.userId
            ).limit(1)
}

fun Database.getAllUserCollectionsByUserId(userId: Int): Query {
    return this.from(UserCollectionTable)
            .innerJoin(
                    right = UserTable,
                    on = UserTable.userId.eq(UserCollectionTable.userId)
            )
            .innerJoin(
                    right = ProfileImageTable,
                    on = ProfileImageTable.id.eq(UserTable.profileImage)
            ).innerJoin(
                    right = CollectionTitleImagesTable,
                    on = CollectionTitleImagesTable.id.eq(UserCollectionTable.collectionUrl)
            ).leftJoin(
                    right = UserCollectionsLikeTable,
                    on = UserCollectionTable.id.eq(UserCollectionsLikeTable.collection_id) and
                            UserCollectionsLikeTable.user_id.eq(userId)
            ).select(
                    UserCollectionTable.id,
                    UserCollectionTable.collectionName,
                    UserCollectionTable.collectionUrl,
                    UserCollectionTable.isCollectionVisible,
                    UserCollectionTable.likesCount,
                    UserCollectionTable.imageNumber,
                    CollectionTitleImagesTable.id,
                    CollectionTitleImagesTable.url,
                    CollectionTitleImagesTable.imagePrice,
                    CollectionTitleImagesTable.primaryImage,
                    UserTable.userId,
                    UserTable.userName,
                    ProfileImageTable.url,
                    UserCollectionsLikeTable.collection_id.isNotNull().aliased("user_liked"),
                    UserTable.userId.eq(userId).aliased("user_owned")
            ).where {
                UserCollectionTable.userId.eq(userId)
            }.groupBy(
                    CollectionTitleImagesTable.id,
                    ProfileImageTable.id,
                    UserCollectionsLikeTable.collection_id,
                    UserCollectionTable.id,
                    UserTable.userId
            ).orderBy(UserCollectionTable.id.desc())
}

fun Database.getAllImageTitleCollectionsQuery(userId: Int, pageSize: Int, pageNumber: Int): Query {
    return this.from(CollectionTitleImagesTable)
            .leftJoin(
                    right = UsersCollectionTitleImageTable,
                    on = CollectionTitleImagesTable.id.eq(UsersCollectionTitleImageTable.collectionTitleImageId) and
                            UsersCollectionTitleImageTable.userId.eq(userId)
            ).select(
                    CollectionTitleImagesTable.id,
                    CollectionTitleImagesTable.url,
                    CollectionTitleImagesTable.primaryImage,
                    CollectionTitleImagesTable.imagePrice,
                    CollectionTitleImagesTable.id,
                    UsersCollectionTitleImageTable.collectionTitleImageId.isNotNull().aliased("is_user_owned")
            ).orderBy(
                    UsersCollectionTitleImageTable.collectionTitleImageId
                            .isNotNull().aliased(
                                    label = "is_user_owned"
                            ).desc(),
                    CollectionTitleImagesTable.id.desc()
            ).limit(pageSize)
            .offset((pageNumber - 1) * pageSize)
}

fun Database.updateUserCollectionTitleImageQuery(collectionTitleId: Int, collectionId: Int): Int {
    return this.update(UserCollectionTable) {
        this.set(UserCollectionTable.collectionUrl, collectionTitleId)
        this.where { UserCollectionTable.id.eq(collectionId) }
    }
}

fun Database.updateUserCollectionTitleTextQuery(title: String, collectionId: Int): Int {
    return this.update(UserCollectionTable) {
        this.set(UserCollectionTable.collectionName, title)
        this.where { UserCollectionTable.id.eq(collectionId) }
    }
}

fun Database.updateImageNumberToUserCollectionQuery(collectionId: Int, isIncrease: Boolean): Int {
    return this.update(UserCollectionTable) {
        this.set(
                UserCollectionTable.imageNumber,
                if (isIncrease) UserCollectionTable.imageNumber + 1
                else UserCollectionTable.imageNumber - 1
        )
        where { it.id.eq(collectionId) }
    }
}

fun Database.addImageToCollectionForUserCollectionsQuery(collectionId: Int, imageId: Int): Int {
    return this.insert(UserImageCollectionTable) {
        this.set(UserImageCollectionTable.collection_id, collectionId)
        this.set(UserImageCollectionTable.image_id, imageId)
    }
}

fun Database.removeImageCollectionForUserCollectionQuery(collectionId: Int, imageId: Int): Int {
    return this.delete(UserImageCollectionTable) {
        it.image_id.eq(imageId) and it.collection_id.eq(collectionId)
    }
}

fun Database.createUserCollectionQuery(collection: CollectionDetailsForCreateNewOne): Int {
    return this.insert(UserCollectionTable) { _ ->
        this.toCollectionEntityByBuilder(collection = collection)
    }
}

fun Database.addUserLikeToCollection(userId: Int, collectionId: Int): Int {
    return this.insert(UserCollectionsLikeTable) {
        this.set(UserCollectionsLikeTable.user_id, userId)
        this.set(UserCollectionsLikeTable.collection_id, collectionId)
    }
}

fun Database.removeUserLikeToCollection(userId: Int, collectionId: Int): Int {
    return this.delete(UserCollectionsLikeTable) {
        it.user_id.eq(userId) and it.collection_id.eq(collectionId)
    }
}

fun Database.checkIfUserLikedUserCollectionQuery(userId: Int, collectionId: Int): Query {
    return this.from(UserCollectionsLikeTable)
            .select(
                    coalesce(
                            count(
                                    UserCollectionsLikeTable.user_id
                            ),
                            defaultValue = 0
                    ).aliased("like_count")
            ).where {
                UserCollectionsLikeTable.user_id.eq(userId) and
                        UserCollectionsLikeTable.collection_id.eq(collectionId)
            }
}

fun Database.updateLikedUserCollectionCountByIncreaseQuery(collectionId: Int): Int {
    return this.update(UserCollectionTable) {
        set(UserCollectionTable.likesCount, UserCollectionTable.likesCount + 1)
        this.where { UserCollectionTable.id.eq(collectionId) }
    }
}

fun Database.updateLikedUserCollectionCountByDecreaseQuery(collectionId: Int): Int {
    return this.update(UserCollectionTable) {
        set(UserCollectionTable.likesCount, UserCollectionTable.likesCount - 1)
        this.where { UserCollectionTable.id.eq(collectionId) }
    }
}

fun Database.updateCollectionVisibility(collectionId: Int, visibility: Boolean): Int {
    return this.update(UserCollectionTable) {
        set(UserCollectionTable.isCollectionVisible, visibility)
        this.where { UserCollectionTable.id.eq(collectionId) }
    }
}

fun Database.deleteUserCollectionQuery(collectionId: Int): Boolean {
    return delete(UserCollectionTable) {
        it.id eq collectionId
    } == 1
}