package com.example.data.setMapper

import com.example.data.tables.UserTable
import org.ktorm.dsl.AssignmentsBuilder
import org.ktorm.support.postgresql.defaultValue

fun AssignmentsBuilder.toUserEntityByBuilder(userName: String, userPassword: String, userSalt: String) {
    this.set(UserTable.userId, UserTable.userId.defaultValue())
    this.set(UserTable.userName, userName)
    this.set(UserTable.userPassword, userPassword)
    this.set(UserTable.userSubscribe, 1)
    this.set(UserTable.userSalt, userSalt)
}

fun AssignmentsBuilder.toUserEntityUpdatePasswordByBuilder(userPassword: String, userSalt: String) {
    this.set(UserTable.userPassword, userPassword)
    this.set(UserTable.userSalt, userSalt)
}