package com.example.employeedirectory

import com.example.employeedirectory.util.Constants.DIRECTORY_HOME_SCREEN

sealed class Screen (val route: String) {
    object DIRECTORY: Screen(DIRECTORY_HOME_SCREEN)
}