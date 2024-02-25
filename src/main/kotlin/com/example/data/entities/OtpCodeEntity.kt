package com.example.data.entities

import org.ktorm.entity.Entity
import java.time.Instant

interface OtpCodeEntity : Entity<OtpCodeEntity> {
    companion object : Entity.Factory<OtpCodeEntity>()

    val codeId: Int
    val codeNumber: String
    val codeDate: Instant
}