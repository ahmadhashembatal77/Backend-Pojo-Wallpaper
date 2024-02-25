package com.example.data.tables

import com.example.data.entities.SubscribeTypesEntity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object SubscribeTypesTable : Table<SubscribeTypesEntity>("subscribe_types") {
    val id = int("id").bindTo { it.id }.primaryKey()
    val subscribe_name = varchar("sub_name").bindTo { it.subscribeName }
}