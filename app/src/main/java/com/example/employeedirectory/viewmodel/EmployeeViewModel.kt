package com.example.employeedirectory.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.employeedirectory.model.repository.EmployeeRepo
import com.example.employeedirectory.model.response.EmployeeResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeViewModel @Inject constructor(
    private val employeeRepo: EmployeeRepo
) : ViewModel() {
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    private val _employees = MutableStateFlow<EmployeeResponse?>(value = null)
    val employees = _employees.asStateFlow()

    var employeeUiState: EmployeeUiState by mutableStateOf(EmployeeUiState.Loading)
        private set

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            employeeUiState = EmployeeUiState.Loading
            _isRefreshing.emit(true)
            employeeUiState = try {
                _employees.value = employeeRepo.getEmployees()
                EmployeeUiState.Success(employeeResponse = employees.value)
            } catch (e: Exception) {
                EmployeeUiState.Error(e.localizedMessage)
            }
            _isRefreshing.emit(false)
        }
    }
}