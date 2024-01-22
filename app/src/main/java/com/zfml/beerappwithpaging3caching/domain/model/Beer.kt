package com.zfml.beerappwithpaging3caching.domain.model

data class Beer(
    val id: Int,
    val name: String,
    val firstBrewed: String,
    val description: String,
    val imageUrl: String,
)
