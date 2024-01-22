package com.zfml.beerappwithpaging3caching.data.local

import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BeerDao {

    @Query("SELECT * FROM BeerEntity")
    fun getAllBeers(): PagingSource<Int,BeerEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllBeers(beers: List<BeerEntity>)

    @Query("SELECT * FROM BeerEntity WHERE id=:id")
    suspend fun getBeerById(id: Int): BeerEntity?

    @Query("DELETE FROM BeerEntity")
    suspend fun clearAllBeers()

}