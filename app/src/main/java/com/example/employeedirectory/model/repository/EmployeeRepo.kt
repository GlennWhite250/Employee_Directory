package com.example.employeedirectory.model.repository

import com.example.employeedirectory.model.remote.EmployeeApi
import com.example.employeedirectory.model.response.Employee
import com.example.employeedirectory.model.response.EmployeeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EmployeeRepo  @Inject constructor(
    private val employeeApi: EmployeeApi
){
    suspend fun getEmployees(): EmployeeResponse {
        return withContext(Dispatchers.IO) {
            employeeApi.fetchEmployees()
        }
    }
}