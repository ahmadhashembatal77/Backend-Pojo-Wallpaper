package com.example.data.entities

import org.ktorm.entity.Entity
import java.time.LocalDate

interface ImageCategories : Entity<ImageCategories> {
    companion object : Entity.Factory<ImageCategories>()

    var id: Int
    var categoryName: String
    var categoryUrl: String
    var blurHash: String
    var categoryDate: LocalDate
}