package com.example.employeedirectory.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.employeedirectory.model.response.Employee
import com.example.employeedirectory.model.response.EmployeeResponse

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DirectoryScreen(employeeList: EmployeeResponse?) {
    LazyColumn(
        modifier = Modifier.padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        val sortedEmployeeList: List<Employee?> = employeeList?.employees?.sortedBy {
            it?.fullName
        } ?: emptyList()
        items(sortedEmployeeList) {
            it?.let {
                EmployeeCard(employee = it)
            }
        }
    }
}
