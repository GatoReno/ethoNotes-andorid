package com.example.ethonotes.ViewModes.Games

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ethonotes.Models.Games.GameList
import com.example.ethonotes.Repository.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class GamesViewModel @Inject constructor(
    private val repo: GameRepository
    ): ViewModel(){

        private val _games = MutableStateFlow<List<GameList>>(emptyList())
    val games = _games.asStateFlow()

    fun initView(){

        sendMessage()
    }
    init {
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

    private val _eventChannel = MutableSharedFlow<UiEvent>(replay = 1)
    val eventFlow = _eventChannel.asSharedFlow()

    fun sendMessage() {
        viewModelScope.launch {
            _eventChannel.tryEmit(UiEvent.ShowToast("Hello from ViewModel :D"))
        }
    }
}

sealed class UiEvent {
    data class ShowToast(val message: String) : UiEvent()
    data object LoginWebView : UiEvent()
}