package com.example.ethonotes.Models

import androidx.compose.runtime.mutableStateOf

data class UserModel(
    val userId: String,
    val email: String,
    val username: String) {

    fun toMap(): MutableMap<String,Any>{
        return mutableMapOf(
            "userId" to this.userId,
            "email" to this.email,
            "username" to this.username
        )
    }

}