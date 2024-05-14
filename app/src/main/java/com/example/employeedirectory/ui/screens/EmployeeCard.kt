package com.example.employeedirectory.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.employeedirectory.R
import com.example.employeedirectory.model.response.Employee

@Composable
fun EmployeeCard(employee: Employee) {

    val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data(employee.photoUrlSmall)
        .memoryCacheKey(employee.photoUrlSmall)
        .placeholder(R.drawable.ic_placeholder)
        .error(R.drawable.ic_error_foreground)
        .fallback(R.drawable.ic_placeholder)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .build()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp,
            pressedElevation = 25.dp,
            focusedElevation = 5.dp
        ),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primaryContainer)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                employee.fullName?.let { Text(text = "Name: $it") } ?: Text(text = " ")
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    AsyncImage(
                        model = imageRequest,
                        contentDescription = "Small profile pic",
                        modifier = Modifier
                            .size(150.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(25.dp))
                    Column(
                        modifier = Modifier.padding(12.dp),
                    ) {
                        Text(text = "About Me!")
                        Spacer(modifier = Modifier.height(10.dp))
                        employee.biography?.let { Text(text = it) }
                    }
                }
                Spacer(modifier = Modifier.height(15.dp))
                employee.uuid?.let {
                    Text(
                        text = "ID: $it",
                        overflow = TextOverflow.Clip,
                        maxLines = 1
                    )
                } ?: Text(text = " ")
                Text(text = "Phone Number: " + employee.phoneNumber)
                Text(text = "Email: " + employee.emailAddress)
                Text(text = "Team: " + employee.team)
                Text(text = "Employee Type: " + employee.employeeType)
            }
        }
    }
}


@Preview
@Composable
fun EmployeeCardPreview() {
    EmployeeCard(
        employee = Employee(
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
}