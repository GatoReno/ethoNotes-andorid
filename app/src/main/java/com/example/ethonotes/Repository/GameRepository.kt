package com.example.ethonotes.Repository

import android.util.Log
import com.example.ethonotes.Abstractions.IGameApi
import com.example.ethonotes.Models.Games.GameList
import javax.inject.Inject

class GameRepository @Inject constructor(private val apiGames: IGameApi) {
    suspend fun getGames(): List<GameList>? {
        val response = apiGames.getGames()

        if (response.isSuccessful){
            return response.body()?.results
        }
        else{
            Log.d("","${response.message()}")
        }
        return null
    }
}