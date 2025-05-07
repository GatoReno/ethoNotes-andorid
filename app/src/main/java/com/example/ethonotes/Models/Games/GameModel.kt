package com.example.ethonotes.Models.Games

data class GameModel(
    val count: Int,
    val results: List<GameList>
)

data class GameList(
    val id: Int,
    val name: String,
    val background: String
)