package com.example.data.source.queries

import com.example.data.dto.user.UserDto
import com.example.data.tables.*
import com.example.domain.queryMapper.user.toUserDetailsDto
import org.ktorm.database.Database
import org.ktorm.dsl.*

fun Database.getUserDetailsByUserId(userId: Int): UserDto {
    return this.from(UserTable)
            .innerJoin(
                    right = SubscribeTypesTable,
                    on = UserTable.userSubscribe eq SubscribeTypesTable.id
            )
            .innerJoin(
                    right = ProfileImageTable,
                    on = ProfileImageTable.id eq UserTable.profileImage
            )
            .innerJoin(
                    right = UserBackgroundImageTable,
                    on = UserBackgroundImageTable.id eq UserTable.background
            )
            .select()
            .where(UserTable.userId eq userId)
            .map { it.toUserDetailsDto() }
            .first()
}

fun Database.checkIfUserExistByUsingUserId(userId: Int): Boolean {
    val result = this.from(UserTable)
            .select(UserTable.userId)
            .where { UserTable.userId eq userId }.map { (it[UserTable.userId] ?: 0) == userId }
    return result.isNotEmpty() && result[0]
}

fun Database.getAllUsersProfileImagesQuery(userId: Int, pageSize: Int, pageNumber: Int): Query {
    return this.from(ProfileImageTable)
            .leftJoin(
                    right = UserImageProfileOwnedTable,
                    on = ProfileImageTable.id.eq(UserImageProfileOwnedTable.profileImageId) and
                            UserImageProfileOwnedTable.userId.eq(userId)
            )
            .select(
                    ProfileImageTable.id,
                    ProfileImageTable.url,
                    ProfileImageTable.primaryImage,
                    ProfileImageTable.imagePrice,
                    ProfileImageTable.forFree,
                    UserImageProfileOwnedTable.profileImageId.isNotNull().aliased("is_user_owned")
            ).orderBy(
                    UserImageProfileOwnedTable.profileImageId.isNotNull().aliased("is_user_owned").desc(),
                    ProfileImageTable.id.desc()
            ).groupBy(
                    ProfileImageTable.id,
                    UserImageProfileOwnedTable.profileImageId
            ).limit(pageSize)
            .offset((pageNumber - 1) * pageSize)
}

fun Database.getAllUsersBackgroundImagesQuery(userId: Int, pageSize: Int, pageNumber: Int): Query {
    return this.from(UserBackgroundImageTable)
            .leftJoin(
                    right = BackgroundImageOwnedTable,
                    on = UserBackgroundImageTable.id.eq(BackgroundImageOwnedTable.backgroundImageId) and
                            BackgroundImageOwnedTable.userId.eq(userId)
            )
            .select(
                    UserBackgroundImageTable.id,
                    UserBackgroundImageTable.url,
                    UserBackgroundImageTable.imagePrice,
                    UserBackgroundImageTable.primaryImage,
                    UserBackgroundImageTable.forFree,
                    BackgroundImageOwnedTable.backgroundImageId.isNotNull().aliased("is_user_owned")
            ).orderBy(
                    UserBackgroundImageTable.id.desc()
            ).groupBy(
                    UserBackgroundImageTable.id,
                    BackgroundImageOwnedTable.backgroundImageId
            ).limit(pageSize)
            .offset((pageNumber - 1) * pageSize)
}

fun Database.getAllUserProfileImagesOwnedQuery(userId: Int): Query {
    return this.from(ProfileImageTable)
            .innerJoin(
                    right = UserImageProfileOwnedTable,
                    on = UserImageProfileOwnedTable.userId.eq(userId)
            )
            .select(
                    ProfileImageTable.id,
                    ProfileImageTable.url,
                    ProfileImageTable.imagePrice,
                    ProfileImageTable.primaryImage,
                    ProfileImageTable.forFree
            ).orderBy(
                    ProfileImageTable.id.desc()
            ).groupBy(
                    UserImageProfileOwnedTable.profileImageId,
                    ProfileImageTable.id
            )
}

fun Database.getAllBackgroundUserImageOwnedQuery(userId: Int): Query {
    return this.from(UserBackgroundImageTable)
            .innerJoin(
                    right = BackgroundImageOwnedTable,
                    on = BackgroundImageOwnedTable.userId.eq(userId)
            )
            .select(
                    UserBackgroundImageTable.id,
                    UserBackgroundImageTable.url,
                    UserBackgroundImageTable.imagePrice,
                    UserBackgroundImageTable.primaryImage,
                    UserBackgroundImageTable.forFree
            ).orderBy(
                    UserBackgroundImageTable.id.desc()
            ).groupBy(
                    BackgroundImageOwnedTable.backgroundImageId,
                    UserBackgroundImageTable.id
            )
}

fun Database.updateUserImageProfileQuery(userId: Int, profileImageId: Int): Int {
    return this.update(UserTable) {
        this.set(UserTable.profileImage, profileImageId)
        this.where { it.userId.eq(userId) }
    }
}

fun Database.updateUserImageBackgroundQuery(userId: Int, backgroundId: Int): Int {
    return this.update(UserTable) {
        this.set(UserTable.background, backgroundId)
        this.where { it.userId.eq(userId) }
    }
}

fun Database.updateUserNameQuery(userId: Int, userName: String): Int {
    return this.update(UserTable) {
        this.set(UserTable.userName, userName)
        this.where { it.userId.eq(userId) }
    }
}

fun Database.makeUserBuyProfileImageQuery(userId: Int, profileImageId: Int): Int {
    return this.insert(UserImageProfileOwnedTable) {
        this.set(UserImageProfileOwnedTable.userId, userId)
        this.set(UserImageProfileOwnedTable.profileImageId, profileImageId)
    }
}

fun Database.makeUserBuyCollectionTitleImageQuery(userId: Int, collectionTitleId: Int): Int {
    return this.insert(UsersCollectionTitleImageTable) {
        this.set(UsersCollectionTitleImageTable.userId, userId)
        this.set(UsersCollectionTitleImageTable.collectionTitleImageId, collectionTitleId)
    }
}

fun Database.makeUserBuyUserBackgroundImageQuery(userId: Int, backgroundImageId: Int): Int {
    return this.insert(BackgroundImageOwnedTable) {
        this.set(BackgroundImageOwnedTable.userId, userId)
        this.set(BackgroundImageOwnedTable.backgroundImageId, backgroundImageId)
    }
}

fun Database.updateUserPojoPriceQuery(userId: Int, increase: Boolean, price: Int): Boolean {
    return this.update(UserTable) {
        if (increase) {
            set(UserTable.pojo_price, UserTable.pojo_price + price)
        } else {
            set(UserTable.pojo_price, UserTable.pojo_price - price)
        }
        where { UserTable.userId eq userId }
    } == 1
}

fun Database.insertOtpCodeQuery(code: String): Boolean {
    return this.insert(OtpCodeTable) { this.set(OtpCodeTable.codeNumber, code) } == 1
}

fun Database.checkCodeIfExist(code: String): Query {
    return this.from(OtpCodeTable)
            .select(OtpCodeTable.codeNumber)
            .where { OtpCodeTable.codeNumber.eq(code) }
            .limit(n = 1)
}