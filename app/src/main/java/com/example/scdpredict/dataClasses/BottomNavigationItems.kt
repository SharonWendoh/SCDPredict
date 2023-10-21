package com.example.scdpredict.dataClasses

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItems(
    val title: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)
