package com.zfml.beerappwithpaging3caching.data.remote

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.zfml.beerappwithpaging3caching.data.local.BeerDatabase
import com.zfml.beerappwithpaging3caching.data.local.BeerEntity
import com.zfml.beerappwithpaging3caching.data.mappers.toBeerEntity
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class BeerRemoteMediator(
    private val beerDatabase: BeerDatabase,
    private val beerApi: BeerApi
): RemoteMediator<Int,BeerEntity>(){
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, BeerEntity>,
    ): MediatorResult {
       return try {
           val loadKey = when(loadType) {
               LoadType.REFRESH -> 1
               LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
               LoadType.APPEND -> {
                   val lastItem = state.lastItemOrNull()
                   if(lastItem == null) {
                       1
                   } else {
                       (lastItem.id/state.config.pageSize) + 1
                   }
               }
           }

           val beers = beerApi.getAllBeers(loadKey,state.config.pageSize)

           beerDatabase.withTransaction {
               if(loadType == LoadType.REFRESH) {
                   beerDatabase.beerDao.clearAllBeers()
               }

               val beerEntitys = beers.map { it.toBeerEntity() }
               beerDatabase.beerDao.insertAllBeers(beerEntitys)

           }
           MediatorResult.Success(endOfPaginationReached = beers.isEmpty())

       }catch (e: IOException) {
           MediatorResult.Error(e)
       }catch (e: HttpException) {
           MediatorResult.Error(e)
       }
    }
}