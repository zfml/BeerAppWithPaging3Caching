package com.zfml.beerappwithpaging3caching.data.mappers

import com.zfml.beerappwithpaging3caching.data.local.BeerEntity
import com.zfml.beerappwithpaging3caching.data.remote.BeerDto
import com.zfml.beerappwithpaging3caching.domain.model.Beer

fun BeerDto.toBeerEntity(): BeerEntity = BeerEntity(
    id = id,
    name = name,
    description = description,
    firstBrewed = first_brewed,
    imageUrl = image_url
)

fun BeerEntity.toBeer(): Beer = Beer(
    id = id,
    name = name,
    description = description,
    firstBrewed = firstBrewed,
    imageUrl = imageUrl
)