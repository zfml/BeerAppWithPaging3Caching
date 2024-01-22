package com.zfml.beerappwithpaging3caching.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BeerEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val firstBrewed: String,
    val description: String,
    val imageUrl: String,
)
