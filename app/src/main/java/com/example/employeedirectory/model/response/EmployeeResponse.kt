package com.example.employeedirectory.model.response


import com.google.gson.annotations.SerializedName

data class EmployeeResponse(
    @SerializedName("employees")
    val employees: List<Employee?>
)