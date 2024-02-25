package com.example.data.entities

import org.ktorm.entity.Entity

interface Tags : Entity<Tags> {
    companion object : Entity.Factory<Tags>()

    var id :Int
    var tagName:String
}