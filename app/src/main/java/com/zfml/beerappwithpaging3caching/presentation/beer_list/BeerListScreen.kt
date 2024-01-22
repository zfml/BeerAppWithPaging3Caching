package com.zfml.beerappwithpaging3caching.presentation.beer_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.zfml.beerappwithpaging3caching.domain.model.Beer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BeerListScreen() {
    val viewModel: BeerListViewModel = hiltViewModel()

    val beersPagingItems = viewModel.beers.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Beers")
            })
        },
        content = { padding ->
            BeerListContent(paddingValues = padding, beerListPagingItems = beersPagingItems)
        }
    )

}

@Composable
fun BeerListContent(
    paddingValues: PaddingValues,
    beerListPagingItems: LazyPagingItems<Beer>
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues)){
        LazyColumn() {
            items(beerListPagingItems.itemCount) { index ->
                beerListPagingItems[index]?.let {  beer ->
                    BeerItem(beer = beer)
                }

            }
        }
    }

}