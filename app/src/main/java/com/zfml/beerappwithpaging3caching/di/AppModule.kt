package com.zfml.beerappwithpaging3caching.di

import android.app.Application
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.zfml.beerappwithpaging3caching.data.local.BeerDatabase
import com.zfml.beerappwithpaging3caching.data.local.BeerEntity
import com.zfml.beerappwithpaging3caching.data.remote.BeerApi
import com.zfml.beerappwithpaging3caching.data.remote.BeerRemoteMediator
import com.zfml.beerappwithpaging3caching.util.Constants.BASE_URL
import com.zfml.beerappwithpaging3caching.util.Constants.BEER_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBeerDatabase(app: Application): BeerDatabase {
        return Room.databaseBuilder(
            app,
            BeerDatabase::class.java,
            BEER_DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideBeerApi(retrofit: Retrofit): BeerApi {
        return retrofit.create(BeerApi::class.java)
    }

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideBeerPager(db: BeerDatabase,api: BeerApi): Pager<Int,BeerEntity> {
        return Pager(
            config = PagingConfig(20),
            remoteMediator = BeerRemoteMediator(db,api),
            pagingSourceFactory = {db.beerDao.getAllBeers()}
        )
    }


}