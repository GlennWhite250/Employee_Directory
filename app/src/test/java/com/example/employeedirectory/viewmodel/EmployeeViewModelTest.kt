package com.example.employeedirectory.viewmodel

import com.example.employeedirectory.model.remote.EmployeeApi
import com.example.employeedirectory.model.repository.EmployeeRepo
import com.example.employeedirectory.model.response.Employee
import com.example.employeedirectory.model.response.EmployeeResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class EmployeeViewModelTest {

    private var mockEmployeeApi = mockk<EmployeeApi>(relaxed = true)


    @Test
    fun successfulEmployeeCall() = runTest {
        val test = buildEmployeeList()
        coEvery { mockEmployeeApi.fetchEmployees() } returns buildEmployeeList()
      val repo = EmployeeRepo(mockEmployeeApi)
        launch { repo.getEmployees() }
        advanceUntilIdle()
        assertEquals(1, repo.getEmployees().employees.size)

        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)

        try {
            val employeeViewModel = EmployeeViewModel(employeeRepo = repo)
            launch { employeeViewModel.refresh() }
            advanceUntilIdle()
            assertEquals(test.employees, employeeViewModel.employees.value?.employees)
            assertEquals(true, employeeViewModel.isRefreshing.value)
        } finally {
            Dispatchers.resetMain()
        }
    }

    companion object {
        fun buildEmployeeList() = EmployeeResponse(
            employees = listOf(
                Employee(
                    uuid = "0d8fcc12-4d0c-425c-8355-390b312b909c",
                    fullName = "Justine Mason",
                    phoneNumber = "5553280123",
                    emailAddress = "jmason.demo@squareup.com",
                    biography = "Engineer on the Point of Sale team.",
                    photoUrlSmall = "https://s3.amazonaws.com/sq-mobile-interview/photos/16c00560-6dd3-4af4-97a6-d4754e7f2394/small.jpg",
                    photoUrlLarge = "https://s3.amazonaws.com/sq-mobile-interview/photos/16c00560-6dd3-4af4-97a6-d4754e7f2394/large.jpg",
                    team = "Point of Sale",
                    employeeType = "FULL_TIME"
                )
            )
        )
    }
}