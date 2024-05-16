package com.example.employeedirectory.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.employeedirectory.viewmodel.EmployeeViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DirectoryHomeScreen(
    navHostController: NavHostController,
    employeeViewModel: EmployeeViewModel = hiltViewModel()
) {

    Scaffold(
        topBar = {
            DirectoryTopBar()
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                NavigationScreen(employeeUiState = employeeViewModel.employeeUiState)
            }
        }
    )
}