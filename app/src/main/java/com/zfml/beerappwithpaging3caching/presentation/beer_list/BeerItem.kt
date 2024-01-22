package com.zfml.beerappwithpaging3caching.presentation.beer_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.zfml.beerappwithpaging3caching.domain.model.Beer

@Composable
fun BeerItem(
    beer: Beer
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
        ,
        tonalElevation = 1.dp
    ) {

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
            ,
        ){
            AsyncImage(
                modifier = Modifier
                    .height(194.dp)
                ,
                model = beer.imageUrl ,
                contentDescription = beer.name,
                contentScale = ContentScale.FillHeight
            )
            Column(
                modifier = Modifier.padding(8.dp),
                verticalArrangement = Arrangement.SpaceAround
            ){
                Text(
                    text = beer.name,
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.titleMedium.fontSize
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = beer.firstBrewed,
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = beer.description,
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

        }
    }
}