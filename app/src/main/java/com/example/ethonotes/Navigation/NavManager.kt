package com.example.ethonotes.Navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ethonotes.ViewModes.LoginViewModel
import com.example.ethonotes.ViewModes.NotesViewModel
import com.example.ethonotes.Views.BlankLoadingView
import com.example.ethonotes.Views.Login.LoginTabView
import com.example.ethonotes.Views.Notes.AddNoteView
import com.example.ethonotes.Views.Notes.HomeView


@Composable

fun NavMaanger(loginVM: LoginViewModel){
    val navController = rememberNavController()
    val notesVM = hiltViewModel<NotesViewModel>()

    NavHost(navController = navController, startDestination = "BlankLoading"){
        composable ("Login"){
            LoginTabView(navController)
        }
        composable ("Home"){ HomeView(navController,notesVM) }
        composable ("AddNote"){ AddNoteView(navController,notesVM) }
        composable ("BlankLoading"){ BlankLoadingView(navController) }
    }
}