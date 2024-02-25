package com.example.data.source.queries

import com.example.data.setMapper.toStickerImageEntityByBuilder
import com.example.data.tables.*
import org.ktorm.database.Database
import org.ktorm.dsl.*

fun Database.getStickersByStickerDateQuery(
        pageSize: Int,
        pageNumber: Int,
        userId: Int
): Query {
    return this.from(StickersTable).leftJoin(
            right = UserStickersOwnedTable,
            on = StickersTable.id.eq(UserStickersOwnedTable.stickerId) and
                    UserStickersOwnedTable.userId.eq(userId)
    ).select(
            StickersTable.id,
            StickersTable.url,
            StickersTable.title,
            StickersTable.watchCount,
            StickersTable.price,
            StickersTable.primary,
            StickersTable.register,
            StickersTable.firstLikeCount,
            StickersTable.secondLikeCount,
            StickersTable.thirdLikeCount,
            StickersTable.fourthLikeCount,
            StickersTable.fifthLikeCount,
            UserStickersOwnedTable.stickerId.isNotNull().aliased("is_user_owned_sticker")
    ).orderBy(
            StickersTable.register.desc()).limit(pageSize).offset((pageNumber - 1) * pageSize
    ).groupBy(
            StickersTable.id,
            UserStickersOwnedTable.stickerId
    )
}

fun Database.getLimitAllStickerCollectionsQuery(collectionLimit: Int): Query {
    return this.from(StickersCollectionsTable).select(
            StickersCollectionsTable.id,
            StickersCollectionsTable.url,
            StickersCollectionsTable.name
    ).limit(n = collectionLimit).orderBy(StickersCollectionsTable.date.desc())
}

fun Database.getStickersFromCollectionQuery(collectionId: Int, userId: Int): Query {
    return this.from(StickersTable)
            .leftJoin(
                    right = UserStickersOwnedTable,
                    on = StickersTable.id.eq(UserStickersOwnedTable.stickerId) and
                            UserStickersOwnedTable.userId.eq(userId)
            ).innerJoin(
                    right = StickersCollectionStickersTable,
                    on = StickersTable.id.eq(StickersCollectionStickersTable.stickerId)
            ).innerJoin(
                    right = StickersCollectionsTable,
                    on = StickersCollectionsTable.id.eq(StickersCollectionStickersTable.collectionStickerId)
            ).select(
                    StickersTable.id,
                    StickersTable.url,
                    StickersTable.title,
                    StickersTable.watchCount,
                    StickersTable.price,
                    StickersTable.primary,
                    StickersTable.register,
                    StickersTable.firstLikeCount,
                    StickersTable.secondLikeCount,
                    StickersTable.thirdLikeCount,
                    StickersTable.fourthLikeCount,
                    StickersTable.fifthLikeCount,
                    UserStickersOwnedTable.stickerId.isNotNull().aliased("is_user_owned_sticker")
            ).where { StickersCollectionsTable.id.eq(collectionId) }.groupBy(
                    StickersTable.id,
                    UserStickersOwnedTable.stickerId
            )
}

fun Database.updateStickerLikeCountBasedOneLikeTypeQuery(likeType: StickerLikeType, stickerId: Int): Int {
    return this.update(StickersTable) {
        when (likeType) {
            StickerLikeType.FirstLikeCount -> set(StickersTable.firstLikeCount, StickersTable.firstLikeCount + 1)
            StickerLikeType.SecondLikeCount -> set(StickersTable.secondLikeCount, StickersTable.secondLikeCount + 1)
            StickerLikeType.ThirdLikeCount -> set(StickersTable.thirdLikeCount, StickersTable.thirdLikeCount + 1)
            StickerLikeType.FourthLikeCount -> set(StickersTable.fourthLikeCount, StickersTable.fourthLikeCount + 1)
            StickerLikeType.FifthLikeCount -> set(StickersTable.fifthLikeCount, StickersTable.fifthLikeCount + 1)
        }
        this.where { StickersTable.id.eq(stickerId) }
    }
}

fun Database.insertStickerToUserStickerOwnedQuery(userId: Int, stickerId: Int): Int {
    return this.insert(UserStickersOwnedTable) {
        this.set(UserStickersOwnedTable.userId, userId)
        this.set(UserStickersOwnedTable.stickerId, stickerId)
    }
}

fun Database.getLimitAllOwnedUserStickersQuery(userId: Int, limit: Int): Query {
    return this.from(StickersTable).innerJoin(
            right = UserStickersOwnedTable,
            on = StickersTable.id.eq(UserStickersOwnedTable.stickerId) and
                    UserStickersOwnedTable.userId.eq(userId)
    ).select(
            StickersTable.id,
            StickersTable.url,
            StickersTable.title,
            StickersTable.watchCount,
            StickersTable.price,
            StickersTable.primary,
            StickersTable.register,
            StickersTable.firstLikeCount,
            StickersTable.secondLikeCount,
            StickersTable.thirdLikeCount,
            StickersTable.fourthLikeCount,
            StickersTable.fifthLikeCount,
            UserStickersOwnedTable.user_like_register,
            UserStickersOwnedTable.stickerId.isNotNull().aliased("is_user_owned_sticker")
    ).limit(limit).orderBy(
            UserStickersOwnedTable.user_like_register.desc()
    ).groupBy(
            StickersTable.id,
            UserStickersOwnedTable.stickerId,
            UserStickersOwnedTable.user_like_register
    )
}

fun Database.insertStickerImageQuery(url: String, codeName: String, id: Int) {
    this.insert(StickersTable) { _ ->
        this.toStickerImageEntityByBuilder(
                url = url,
                name = codeName,
                id = id
        )
    }
}

enum class StickerLikeType {
    FirstLikeCount,
    SecondLikeCount,
    ThirdLikeCount,
    FourthLikeCount,
    FifthLikeCount
}