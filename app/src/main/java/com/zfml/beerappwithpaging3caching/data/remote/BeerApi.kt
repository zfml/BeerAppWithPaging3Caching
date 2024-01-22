package com.zfml.beerappwithpaging3caching.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface BeerApi {

    @GET("beers")
    suspend fun getAllBeers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<BeerDto>

}