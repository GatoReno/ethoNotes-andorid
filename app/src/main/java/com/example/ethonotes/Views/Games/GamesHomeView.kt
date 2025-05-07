package com.example.ethonotes.Views.Games

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.ethonotes.ViewModes.Games.GamesViewModel
import androidx.compose.runtime.getValue

@Composable
fun GamesHomeView(viewModel: GamesViewModel){

    val games by viewModel.games.collectAsState()

    LazyColumn {
        items(games){item->
            Text(text = item.name)
        }
    }
}