package com.flomaskine.businesscardgenerator.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "business_cards")
data class Card(
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        val name: String,
        val company: String,
        val phone: String,
        val email: String,
        val customBg: String,
)