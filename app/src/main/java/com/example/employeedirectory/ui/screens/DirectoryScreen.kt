package com.example.employeedirectory.ui.screens

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.employeedirectory.viewmodel.EmployeeViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DirectoryScreen(
    employeeViewModel: EmployeeViewModel = hiltViewModel()
) {
    val isRefreshing by employeeViewModel.isRefreshing.collectAsStateWithLifecycle()
    val employeeList by employeeViewModel.employees.collectAsStateWithLifecycle()

    if (employeeList != null) {
        employeeList?.employees?.let {
            PullToRefreshLazyColumn(
                items = it.sortedBy { it?.fullName },
                content = { employee ->
                    if (employee != null) {
                        EmployeeCard(employee = employee)
                    }
                },
                isRefreshing = isRefreshing,
                onRefresh = { employeeViewModel.refresh() }
            )
        }
    }

}
