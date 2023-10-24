package com.example.scdpredict.viewmodels

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.lifecycle.ViewModel
import com.example.scdpredict.dataClasses.BottomNavigationItems

class BottomAppBarViewModel : ViewModel() {
    val items = listOf(

        BottomNavigationItems(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unSelectedIcon = Icons.Outlined.Home,
            hasNews = false,
        ),
        BottomNavigationItems(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unSelectedIcon = Icons.Outlined.Home,
            hasNews = false,
            badgeCount = 45
        ),
        BottomNavigationItems(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unSelectedIcon = Icons.Outlined.Home,
            hasNews = true,
        )
    )
}