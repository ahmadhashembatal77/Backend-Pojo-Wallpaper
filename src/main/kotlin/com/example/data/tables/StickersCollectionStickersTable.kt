package com.example.data.tables

import com.example.data.entities.StickersCollectionStickersEntity
import org.ktorm.schema.Table
import org.ktorm.schema.int

object StickersCollectionStickersTable  : Table<StickersCollectionStickersEntity>("sticker_collections_stickers") {
    val stickerId = int("sticker_id").references(StickersTable) { it.stickerId }
    val collectionStickerId = int("collection_id").references(StickersCollectionsTable) { it.collectionId }
}