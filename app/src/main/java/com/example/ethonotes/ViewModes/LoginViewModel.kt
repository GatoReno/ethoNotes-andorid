package com.example.ethonotes.ViewModes

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ethonotes.Components.Alert
import com.example.ethonotes.Models.UserModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel(){

    private val auth: FirebaseAuth = Firebase.auth
    var showAlert by mutableStateOf(false)
    var fibAlertmsn by mutableStateOf("")

    fun loginEmail(email: String, pass: String, onSuccess:() -> Unit){
       viewModelScope.launch {
           try {
                auth.signInWithEmailAndPassword(email,pass)
                    .addOnCompleteListener { task->
                        if(task.isSuccessful){
                            onSuccess()
                        }else{
                            Log.d("Error FiB","Fib Error")
                            showAlert = true
                        }
                    }
                    .addOnFailureListener { {  Log.d("Error FiB loginEmail","Fib addOnFailureListener") } }
           }catch (e: Exception)
           {
                Log.d("Error JP","${e.message} ${e.cause}")
           }
       }
    }

    fun createUser(email: String, username:String, pass: String, onSuccess:() -> Unit){
        viewModelScope.launch {
            try {
                auth.createUserWithEmailAndPassword(email,pass)
                    .addOnCompleteListener { task->
                        if(task.isSuccessful){
                            saveUser(username)
                            onSuccess()
                        }else{
                            Log.d("Error FiB","Fib Error")
                            showAlert = true
                        }
                    }
                    .addOnFailureListener {
                            exception ->
                        Log.d("Error FiB createUser","Fib addOnFailureListener ${exception.message}")
                        fibAlertmsn = "${exception.message}"
                    }
            }catch (e: Exception)
            {
                Log.d("Error JP","${e.message} ${e.cause}")
            }
        }
    }

    private fun saveUser(username: String){
        val id = auth.currentUser?.uid
        val email = auth.currentUser?.email

        viewModelScope.launch(Dispatchers.IO){
            val user = UserModel(userId = id.toString(), email = email.toString(), username = username )

            FirebaseFirestore.getInstance().collection("Users")
                .add(user)
                .addOnFailureListener { Log.d("Error FiB FirebaseFirestore","Fib addOnFailureListener")  }
                .addOnSuccessListener {  }
        }

    }

    fun closeAlert(){
        showAlert = false
    }

}