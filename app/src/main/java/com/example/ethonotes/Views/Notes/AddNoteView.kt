@file:Suppress("PreviewAnnotationInFunctionWithParameters")

package com.example.ethonotes.Views.Notes
import android.widget.Toast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ethonotes.Components.Alert
import com.example.ethonotes.ViewModes.NotesViewModel

@Preview
@Composable
fun AddNoteView(navController: NavController, notesVM: NotesViewModel){
    var title by remember {mutableStateOf("")}
    var note by remember {mutableStateOf("")}
    val context = LocalContext.current
    val isDarkTheme = isSystemInDarkTheme()

    Scaffold(
      topBar = {
          TopAppBar (title = {Text(text = "New Note")},
              navigationIcon = { IconButton(onClick = {
                  navController.popBackStack()
              }){
                  Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
              }},
              actions = {IconButton(onClick = {
                    notesVM.saveNoteFireStore (title,note){
                        Toast.makeText(context,"Saved", Toast.LENGTH_LONG).show()
                        navController.popBackStack()
                    }
              }) {
                Icon(imageVector = Icons.Default.AddCircle, contentDescription = "")
              }}

              )
      }
    ){ pad->
        Column (modifier = Modifier
            .padding(pad)
            .fillMaxSize()
        ){
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(value = title,
                onValueChange = {title = it},
                label = {Text(text = "Title")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp))
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(value = note,
                onValueChange = {note = it},
                label = {Text(text = "Add Note")},
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxHeight()
                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
            )
        }
    }

    if (notesVM.showAlert){
        Alert(
            title = "Alert",
            message = notesVM.fibAlertmsn,
            confirmTxt = "ok",
            onConfirmClick = { notesVM.closeAlert()},
            onDismissClick = {})
    }

}