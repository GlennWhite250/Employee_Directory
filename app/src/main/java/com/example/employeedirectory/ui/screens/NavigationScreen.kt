package com.example.employeedirectory.ui.screens

import androidx.compose.runtime.Composable
import com.example.employeedirectory.viewmodel.EmployeeUiState

@Composable
fun NavigationScreen(employeeUiState: EmployeeUiState) {
    when(employeeUiState) {
        is EmployeeUiState.Loading -> LoadingScreen()
        is EmployeeUiState.Error -> ErrorScreen(errorMessage = employeeUiState.errorMessage)
        is EmployeeUiState.Success -> DirectoryScreen()
    }
}