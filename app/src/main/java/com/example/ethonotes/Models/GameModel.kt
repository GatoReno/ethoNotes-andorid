package com.example.ethonotes.Models


data class GameModel (
   val count:Int,
    val results: List<GameList>
)

data class GameList(
    val count:Int,
    val name: String,
    val bg_image: String,
)