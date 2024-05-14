package com.example.employeedirectory.viewmodel

import com.example.employeedirectory.model.response.EmployeeResponse

sealed interface EmployeeUiState {
    data class Success(val employeeResponse: EmployeeResponse?) : EmployeeUiState
    data class Error(val errorMessage: String?) : EmployeeUiState
    object Loading : EmployeeUiState
}