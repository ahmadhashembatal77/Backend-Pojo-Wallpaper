package com.example.data.tables

import com.example.data.entities.StickersCollectionsEntity
import org.ktorm.schema.*

object StickersCollectionsTable : Table<StickersCollectionsEntity>("sticker_collections") {
    val id = int("collection_id").bindTo { it.id }.primaryKey()
    val url = varchar("collection_url").bindTo { it.url }
    val name = varchar("collection_name").bindTo { it.name }
    val date = date("collection_add_date").bindTo { it.date }
}