package com.example.ramk.core.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_character")
data class CharacterEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val status: String,
    val type: String,
    val gender: String,
    val location: String,
    val isFavourite: Boolean = true,
    val image: String,
    val species: String,
    val origin: String,
    val addedTimestamp: Long = System.currentTimeMillis()
)
