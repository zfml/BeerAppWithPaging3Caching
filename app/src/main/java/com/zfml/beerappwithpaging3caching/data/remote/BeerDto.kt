package com.zfml.beerappwithpaging3caching.data.remote

data class BeerDto(
    val id: Int,
    val name: String,
    val first_brewed: String,
    val description: String,
    val image_url: String
)
