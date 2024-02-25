package com.example.data.source.dao

import com.example.data.dto.user.*

interface UserDao {

    suspend fun insertUser(userName: String, userPassword: String, userSalt: String): UserDto
    suspend fun updateUserPassword(userId: Int,userPassword: String, userSalt: String): Boolean
    suspend fun deleteUser(username: String): Boolean
    suspend fun checkIfUserNameExist(username: String): Boolean
    suspend fun checkIfUserEmailExist(email: String): Boolean
    suspend fun getUserByUserName(userName: String): SmallDetailsUserDto
    suspend fun getUserByUserEmail(email: String): SmallDetailsUserDto
    suspend fun getUserInformationByUserId(userId: Int): UserDto
    suspend fun checkIfUserExistOrNotByUserId(userId: Int): Boolean
    suspend fun getAllUsersProfileImages(userId: Int, pageSize: Int, pageNumber: Int)
            : List<UserProfileImageWithUserOwnedDto>

    suspend fun getAllUsersBackgroundImages(userId: Int, pageSize: Int, pageNumber: Int)
            : List<UserBackgroundImageWithUserOwnedDto>

    suspend fun getOwnedUserProfileImages(userId: Int): List<UserProfileImageDto>
    suspend fun getOwnedUserBackgroundImages(userId: Int): List<UserBackgroundImageDto>
    suspend fun updateUserImageProfile(userId: Int, profileImageId: Int): Int
    suspend fun updateUserImageBackground(userId: Int, backgroundId: Int): Int
    suspend fun updateUserName(userId: Int, userName: String): Int
    suspend fun makeUserBuyProfileImage(userId: Int, profileImageId: Int): Int
    suspend fun makeUserBuyCollectionTitleImage(userId: Int, collectionTitleId: Int): Int
    suspend fun makeUserBuyUserBackgroundImage(userId: Int, backgroundImageId: Int): Int
    suspend fun updateUserPojoPrice(userId: Int, increase: Boolean, price: Int): Boolean
}