package com.example.ethonotes.ViewModes.Games

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ethonotes.Models.Games.GameList
import com.example.ethonotes.Repository.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class GamesViewModel @Inject constructor(
    private val repo: GameRepository
    ): ViewModel(){

        private val _games = MutableStateFlow<List<GameList>>(emptyList())
    val games = _games.asStateFlow()

    init
    {
        fetchGames()
    }

    private fun fetchGames(){
        viewModelScope.launch {

            withContext(Dispatchers.IO) {
                val result = repo.getGames()
                _games.value= result?: emptyList()
            }
        }
    }
}