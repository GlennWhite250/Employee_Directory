package com.example.employeedirectory.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.employeedirectory.R

@Composable
fun LoadingScreen() {
    Image(
        painter = painterResource(id = R.drawable.loading_img),
        contentDescription = "Loading...",
        modifier = Modifier
            .size(200.dp)
            .fillMaxSize()
    )
}


@Preview
@Composable
fun LoadingScreenPreview() {
    LoadingScreen()
}