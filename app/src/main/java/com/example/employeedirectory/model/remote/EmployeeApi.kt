package com.example.employeedirectory.model.remote

import com.example.employeedirectory.model.response.EmployeeResponse
import com.example.employeedirectory.util.Constants.END_POINT
import retrofit2.http.GET

interface EmployeeApi {
    @GET(END_POINT)
    suspend fun fetchEmployees(): EmployeeResponse
}