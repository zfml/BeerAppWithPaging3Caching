package com.zfml.beerappwithpaging3caching.presentation.beer_list

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.map
import com.zfml.beerappwithpaging3caching.data.local.BeerEntity
import com.zfml.beerappwithpaging3caching.data.mappers.toBeer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class BeerListViewModel @Inject constructor(
    private val pager: Pager<Int,BeerEntity>
): ViewModel() {

    val beers = pager.flow.map { it.map { it.toBeer() } }

}