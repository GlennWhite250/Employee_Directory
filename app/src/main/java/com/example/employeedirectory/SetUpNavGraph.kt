package com.example.employeedirectory

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.employeedirectory.ui.screens.DirectoryHomeScreen

@Composable
fun SetUpNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.DIRECTORY.route
    ) {
        composable(route = Screen.DIRECTORY.route) {
            DirectoryHomeScreen(navHostController = navHostController)
        }
    }
}