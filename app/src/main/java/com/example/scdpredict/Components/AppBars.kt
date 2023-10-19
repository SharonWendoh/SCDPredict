package com.example.scdpredict.Components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LargeTopAppBar() {
    val title: String
    val text: String
    val scrollBehaviour =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

}
