package com.example.ethonotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.ethonotes.Navigation.NavMaanger
import com.example.ethonotes.ViewModes.LoginViewModel
import com.example.ethonotes.ViewModes.NotesViewModel
import com.example.ethonotes.Views.Login.LoginTabView
import com.example.ethonotes.ui.theme.EthoNotesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val loginVM  : LoginViewModel by viewModels()
        val notesVM  : NotesViewModel by viewModels()
        enableEdgeToEdge()
        setContent {

            EthoNotesTheme {
                val isDarkTheme = isSystemInDarkTheme()
                val backgroundColor = if (isDarkTheme) Color.Black else Color.White
                 Column(modifier = Modifier
                     .fillMaxSize()
                     .background(backgroundColor)
                     .padding(WindowInsets.systemBars.asPaddingValues())) {
                     NavMaanger(loginVM,notesVM)
                 }


            }
        }
    }
}

