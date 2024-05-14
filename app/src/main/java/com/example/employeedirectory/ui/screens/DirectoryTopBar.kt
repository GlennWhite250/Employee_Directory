package com.example.employeedirectory.ui.screens

import androidx.compose.material.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DirectoryTopBar() {
    TopAppBar(
        title = {
            Text(
                text = "The Company Directory",
                color = MaterialTheme.colorScheme.primary
            )
        },
        backgroundColor = MaterialTheme.colorScheme.primaryContainer
    )
}

@Composable
@Preview
fun DirectoryTopBarPreview() {
    DirectoryTopBar()
}