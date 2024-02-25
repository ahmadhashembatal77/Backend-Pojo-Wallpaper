package com.example.data.source.dao

import com.example.data.dto.user.*
import com.example.data.setMapper.toUserEntityByBuilder
import com.example.data.setMapper.toUserEntityUpdatePasswordByBuilder
import com.example.data.source.queries.*
import com.example.data.tables.UserTable
import com.example.domain.queryMapper.user.*
import org.ktorm.database.Database
import org.ktorm.dsl.*

class UserDaoImpl(
        private var dataBase: Database,
) : UserDao {

    override suspend fun checkIfUserNameExist(username: String): Boolean {
        return dataBase.checkIfExistByNameAndTableName(UserTable.userName, username)
    }

    override suspend fun checkIfUserEmailExist(email: String): Boolean {
        return dataBase.checkIfExistByNameAndTableName(UserTable.email, email)
    }

    override suspend fun getUserByUserName(userName: String): SmallDetailsUserDto {
        return dataBase.from(UserTable).select(
                UserTable.userId,
                UserTable.userName,
                UserTable.email,
                UserTable.userPassword,
                UserTable.userSalt
        ).where { UserTable.userName eq userName }.map {
            it.toSmallDetailsUserDto()
        }.first()
    }

    override suspend fun getUserByUserEmail(email: String): SmallDetailsUserDto {
        return dataBase.from(UserTable).select(
                UserTable.userId,
                UserTable.userName,
                UserTable.email,
                UserTable.userPassword,
                UserTable.userSalt
        ).where { UserTable.email eq email }.map {
            it.toSmallDetailsUserDto()
        }.first()
    }

    override suspend fun insertUser(userName: String, userPassword: String, userSalt: String): UserDto {
        dataBase.insert(UserTable) { _ ->
            this.toUserEntityByBuilder(userName = userName, userPassword = userPassword, userSalt = userSalt)
        }
        return dataBase.from(UserTable).select()
                .where { UserTable.userName eq userName }.map {
                    it.toUserDetailsDto()
                }.first()
    }

    override suspend fun updateUserPassword(userId: Int, userPassword: String, userSalt: String): Boolean {
        return dataBase.update(UserTable) {
            this.toUserEntityUpdatePasswordByBuilder(userPassword, userSalt)
            where { UserTable.userId eq userId }
        } == 1
    }

    override suspend fun deleteUser(username: String): Boolean {
        return dataBase.delete(UserTable) {
            it.userName eq username
        } == 1
    }

    override suspend fun getUserInformationByUserId(userId: Int): UserDto {
        return dataBase.getUserDetailsByUserId(userId = userId)
    }

    override suspend fun checkIfUserExistOrNotByUserId(userId: Int): Boolean {
        return dataBase.checkIfUserExistByUsingUserId(userId)
    }

    override suspend fun getAllUsersProfileImages(userId: Int, pageSize: Int, pageNumber: Int)
            : List<UserProfileImageWithUserOwnedDto> {
        return dataBase.getAllUsersProfileImagesQuery(userId = userId, pageSize = pageSize, pageNumber = pageNumber)
                .map {
                    it.toUserProfileImageWithUserOwnedDto()
                }
    }

    override suspend fun getAllUsersBackgroundImages(userId: Int, pageSize: Int, pageNumber: Int)
            : List<UserBackgroundImageWithUserOwnedDto> {
        return dataBase.getAllUsersBackgroundImagesQuery(userId = userId, pageSize = pageSize, pageNumber = pageNumber)
                .map {
                    it.toUserBackgroundWithUserOwnedDto()
                }
    }

    override suspend fun getOwnedUserProfileImages(userId: Int): List<UserProfileImageDto> {
        return dataBase.getAllUserProfileImagesOwnedQuery(userId = userId).map {
            it.toUserProfileImageDto()
        }
    }

    override suspend fun getOwnedUserBackgroundImages(userId: Int): List<UserBackgroundImageDto> {
        return dataBase.getAllBackgroundUserImageOwnedQuery(userId = userId).map {
            it.toUserBackgroundDto()
        }
    }

    override suspend fun updateUserImageProfile(userId: Int, profileImageId: Int): Int {
        return dataBase.updateUserImageProfileQuery(userId = userId, profileImageId = profileImageId)
    }

    override suspend fun updateUserImageBackground(userId: Int, backgroundId: Int): Int {
        return dataBase.updateUserImageBackgroundQuery(userId = userId, backgroundId = backgroundId)
    }

    override suspend fun updateUserName(userId: Int, userName: String): Int {
        return dataBase.updateUserNameQuery(userId = userId, userName = userName)
    }

    override suspend fun makeUserBuyProfileImage(userId: Int, profileImageId: Int): Int {
        return dataBase.makeUserBuyProfileImageQuery(userId = userId, profileImageId = profileImageId)
    }

    override suspend fun makeUserBuyCollectionTitleImage(userId: Int, collectionTitleId: Int): Int {
        return dataBase.makeUserBuyCollectionTitleImageQuery(userId = userId, collectionTitleId = collectionTitleId)
    }

    override suspend fun makeUserBuyUserBackgroundImage(userId: Int, backgroundImageId: Int): Int {
        return dataBase.makeUserBuyUserBackgroundImageQuery(userId = userId, backgroundImageId = backgroundImageId)
    }

    override suspend fun updateUserPojoPrice(userId: Int, increase: Boolean, price: Int): Boolean {
        return dataBase.updateUserPojoPriceQuery(userId = userId, increase = increase, price = price)
    }
}