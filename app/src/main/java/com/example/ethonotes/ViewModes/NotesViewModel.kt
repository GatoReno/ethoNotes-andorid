package com.example.ethonotes.ViewModes

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class NotesViewModel: ViewModel(){

    private val auth: FirebaseAuth = Firebase.auth
    private val firestore = Firebase.firestore
    var showAlert by mutableStateOf(false)
    var fibAlertmsn by mutableStateOf("")

    fun signOut(){
        auth.signOut()
    }

    fun saveNoteFireStore(title: String , note: String, onSuccess:()->Unit){
        val email = auth.currentUser?.email
        viewModelScope.launch (Dispatchers.IO){
            try {
                    val newNote = hashMapOf(
                        "title" to title,
                        "note" to note,
                        "date" to formatDate(),
                        "emailUser" to email
                    )

                firestore.collection("Notes").add(newNote)
                    .addOnSuccessListener {
                        onSuccess()
                    }

            }catch (e: Exception){
                Log.d("Fib Error","Error> ${e.message} ${e.cause}")
            }
        }
    }

    private fun formatDate() : String {
        val currrentDate : Date = Calendar.getInstance().time
        val res = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return res.format(currrentDate)
    }


    fun closeAlert(){
        showAlert = false
    }

}