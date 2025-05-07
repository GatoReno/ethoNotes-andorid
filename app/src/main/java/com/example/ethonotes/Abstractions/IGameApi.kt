package com.example.ethonotes.Abstractions

import com.example.ethonotes.Models.Games.GameModel
import com.example.ethonotes.Util.Constants.Companion.PK
import com.example.ethonotes.Util.Constants.Companion.ENDPOINT
import retrofit2.Response

import retrofit2.http.GET

interface IGameApi {

    @GET(ENDPOINT + PK)
    suspend fun  getGames(): Response<GameModel>


}