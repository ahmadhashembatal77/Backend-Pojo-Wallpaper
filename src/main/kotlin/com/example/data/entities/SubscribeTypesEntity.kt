package com.example.data.entities

import org.ktorm.entity.Entity

interface SubscribeTypesEntity : Entity<SubscribeTypesEntity> {
    companion object : Entity.Factory<SubscribeTypesEntity>()

    var id: Int
    var subscribeName: String
}