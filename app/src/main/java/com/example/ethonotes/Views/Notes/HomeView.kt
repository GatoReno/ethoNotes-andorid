@file:Suppress("PreviewAnnotationInFunctionWithParameters")

package com.example.ethonotes.Views.Notes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.ethonotes.ViewModes.NotesViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun HomeView(navController: NavController, notesVM: NotesViewModel) {
    //Text(text = "HomeView",color= MaterialTheme.colorScheme.onBackground)
    Scaffold(topBar = {
        TopAppBar(

            title = {Text(text = "Notes")},
            navigationIcon = {
                IconButton(onClick = {
                    notesVM.signOut()
                    navController.popBackStack()
                }) {
                    Icon(
                        imageVector = Icons.Default.ExitToApp,
                        contentDescription = ""
                        )
                }

            },
            actions = {
                IconButton(onClick = {

                    navController.navigate("AddNote")
                }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = ""
                    )
                }
            }
        )
    }){ pad ->
        Column (
            modifier = Modifier.padding(pad),
            horizontalAlignment = Alignment.CenterHorizontally
            ){

            Text(text = "")
        }


    }
}