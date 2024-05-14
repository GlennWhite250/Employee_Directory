package com.example.employeedirectory.viewmodel

import com.example.employeedirectory.model.repository.EmployeeRepo
import com.example.employeedirectory.model.response.Employee
import com.example.employeedirectory.model.response.EmployeeResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@OptIn(ExperimentalCoroutinesApi::class)
class EmployeeViewModelTest {

    private val dataSource = mockk<EmployeeRepo>(relaxed = true)

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun successfulEmployeeCall() = runTest {
        val testData = buildEmployeeList()
        coEvery { dataSource.getEmployees() } returns buildEmployeeList()
        val sut = EmployeeViewModel(employeeRepo = dataSource)
        launch { sut.refresh() }
        advanceUntilIdle()
        val data = sut.employees.value

        assertEquals(testData, data)
        assertEquals(false, sut.isRefreshing.value)
        assertEquals(EmployeeUiState.Success(employeeResponse = testData), sut.employeeUiState)
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

@OptIn(ExperimentalCoroutinesApi::class)
class MainDispatcherRule(
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
) : TestWatcher() {
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}