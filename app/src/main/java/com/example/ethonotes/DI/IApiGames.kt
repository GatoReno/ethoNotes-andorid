package com.example.ethonotes.DI

import com.example.ethonotes.DI.Const.Companion.Endpoint
import com.example.ethonotes.DI.Const.Companion.PK
import com.example.ethonotes.Models.GameModel
import retrofit2.Response
import retrofit2.http.GET

interface  IApiGames{
    @GET(Endpoint + PK)
    suspend fun getGames():  Response<GameModel>
}