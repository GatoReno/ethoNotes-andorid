package com.example.ethonotes.Views.Login

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ethonotes.ViewModes.LoginViewModel


@Composable
fun LoginTabView(navController: NavController, loginVM: LoginViewModel = hiltViewModel<LoginViewModel>()){


    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Login","Register")

    Column {
        TabRow(selectedTabIndex =  selectedTab,
            indicator = {tabPositon ->
                TabRowDefaults.Indicator(Modifier.tabIndicatorOffset(tabPositon[selectedTab]))
            }
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(selected = selectedTab == index,
                    onClick = { selectedTab = index},
                    text = {Text(text = title)})

                }
            }
            when(selectedTab){
                0 -> LoginView(navController, loginVM)
                1 -> RegisterView(navController,loginVM)
            }
        }

}