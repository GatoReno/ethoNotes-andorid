package com.example.ethonotes.Views.Games

import android.widget.Toast
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.ethonotes.ViewModes.Games.GamesViewModel
import com.example.ethonotes.ViewModes.Games.UiEvent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun GamesHomeView(viewModel: GamesViewModel = hiltViewModel<GamesViewModel>()){

    val games by viewModel.games.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(true) {
        viewModel.initView()
        viewModel.eventFlow.collectLatest {
            when(it){
                UiEvent.LoginWebView -> {
                    TODO()
                }
                is UiEvent.ShowToast -> {
                    Toast.makeText(context,it.message,Toast.LENGTH_LONG).show()
                }
            }
        }
    }


    LazyColumn {
        items(games){item->
            Text(text = item.name)
        }
    }
}

