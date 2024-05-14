package com.example.employeedirectory.model.repository

import com.example.employeedirectory.MainDispatcherRule
import com.example.employeedirectory.model.remote.EmployeeApi
import com.example.employeedirectory.model.response.Employee
import com.example.employeedirectory.model.response.EmployeeResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class EmployeeRepoTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val dataSource = mockk<EmployeeApi>(relaxed = true)

    @Test
    fun successfulNetworkCallTest() = runTest {
        val testData = buildEmployeeList()
        coEvery { dataSource.fetchEmployees() } returns buildEmployeeList()
        val sut = EmployeeRepo(employeeApi = dataSource)
        launch { sut.getEmployees() }
        advanceUntilIdle()
        val data = sut.getEmployees()
        assertEquals(testData, data)
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