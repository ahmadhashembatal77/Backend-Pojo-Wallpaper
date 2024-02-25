package com.example.data.tables

import com.example.data.entities.OtpCodeEntity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.timestamp
import org.ktorm.schema.varchar

object OtpCodeTable : Table<OtpCodeEntity>("otp_code") {
    val codeId = int("code_id").bindTo { it.codeId }.primaryKey()
    val codeNumber = varchar("code_number").bindTo { it.codeNumber }
    val codeDate = timestamp("code_date").bindTo { it.codeDate }
}