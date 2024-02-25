package com.example.data.setMapper

import com.example.data.tables.*
import org.ktorm.dsl.AssignmentsBuilder
import org.ktorm.support.postgresql.defaultValue

fun AssignmentsBuilder.toImageEntityByBuilder(categoryId: Int, blurHash: String, likeCount: Int, imageTitle: String) {
    this.set(ImageDetailsTable.id, ImageDetailsTable.id.defaultValue())
    this.set(ImageDetailsTable.categoryId, categoryId)
    this.set(ImageDetailsTable.imgTitle, imageTitle)
    this.set(ImageDetailsTable.blur_hash, blurHash)
    this.set(ImageDetailsTable.likeCount, likeCount)
    this.set(ImageDetailsTable.watchCount, likeCount)
}

fun AssignmentsBuilder.toCollectionTitleImageEntityByBuilder(price: Int, url: String, codeName: String, id: Int) {
    this.set(CollectionTitleImagesTable.id, id)
    this.set(CollectionTitleImagesTable.imagePrice, price)
    this.set(CollectionTitleImagesTable.url, url)
    this.set(CollectionTitleImagesTable.codeName, codeName)
}

fun AssignmentsBuilder.toSmallImageEntityByBuilder(price: Int, url: String, codeName: String, id: Int) {
    this.set(ProfileImageTable.id, id)
    this.set(ProfileImageTable.imagePrice, price)
    this.set(ProfileImageTable.url, url)
    this.set(ProfileImageTable.codeName, codeName)
}

fun AssignmentsBuilder.toBackgroundImageEntityByBuilder(price: Int, url: String, codeName: String, id: Int) {
    this.set(UserBackgroundImageTable.id, id)
    this.set(UserBackgroundImageTable.imagePrice, price)
    this.set(UserBackgroundImageTable.url, url)
    this.set(UserBackgroundImageTable.codeName, codeName)
}

fun AssignmentsBuilder.toStickerImageEntityByBuilder(url: String, name: String, id: Int) {
    this.set(StickersTable.id, id)
    this.set(StickersTable.codeName, name)
    this.set(StickersTable.title, name)
    this.set(StickersTable.url, url)
    this.set(StickersTable.watchCount, (100..3000).random())
    this.set(StickersTable.price, 0)
    this.set(StickersTable.primary, false)
    this.set(StickersTable.firstLikeCount, (100..3000).random())
    this.set(StickersTable.secondLikeCount, (100..3000).random())
    this.set(StickersTable.thirdLikeCount, (100..3000).random())
    this.set(StickersTable.fourthLikeCount, (100..3000).random())
    this.set(StickersTable.fifthLikeCount, (100..3000).random())
}