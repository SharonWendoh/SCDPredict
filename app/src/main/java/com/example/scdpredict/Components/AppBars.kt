package com.example.scdpredict.Components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.scdpredict.R
import com.example.scdpredict.dataClasses.BottomNavigationItems
import com.example.scdpredict.ui.theme.SCDPredictTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.scdpredict.viewmodels.BottomAppBarViewModel
import com.google.android.material.bottomappbar.BottomAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    userName: String,
    logoutOnClick: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior
){

    MediumTopAppBar(
        title = {
            Row(){
                Text(text = "Hello, ")
                Text(text = userName)
            }
        },
        navigationIcon = {
            IconButton(
                onClick = {}) {
                Icon(painter = painterResource(
                    id = R.drawable.calendar) ,
                    contentDescription = "")
            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(painter = painterResource(id = R.drawable.notification),
                    contentDescription = "Notifications" )
            }
            IconButton(onClick = logoutOnClick) {
                Icon(painter = painterResource(id = R.drawable.settings),
                    contentDescription = "Settings" )
            }
        },
        scrollBehavior = scrollBehavior
        )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackNavigateTopAppBar(
    navController: NavController,
    title : String
){
    CenterAlignedTopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(
                onClick = {navController.popBackStack()}) {
                Icon(painter = painterResource(
                    id = R.drawable.backarrow) ,
                    contentDescription = "")
            }
        },
        actions = {
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.more
                    ),
                    contentDescription = ""
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomAppBar(
    onHomeClick: () -> Unit,
    onAddClick: () -> Unit,
    onAccountClick: () -> Unit
){
    val items = listOf(

        BottomNavigationItems(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unSelectedIcon = Icons.Outlined.Home,
            hasNews = false,
        ),
        BottomNavigationItems(
            title = "add",
            selectedIcon = Icons.Filled.Add,
            unSelectedIcon = Icons.Outlined.Add,
            hasNews = false,
            badgeCount = 45
        ),
        BottomNavigationItems(
        title = "Account",
        selectedIcon = Icons.Filled.AccountCircle,
        unSelectedIcon = Icons.Outlined.AccountCircle,
        hasNews = true,
        )
    )
    //val viewModel: BottomAppBarViewModel = rememberViewModel()
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }
        NavigationBar {
            items.forEachIndexed{
                index, item ->
                NavigationBarItem(
                    selected = selectedItemIndex == index,
                    onClick = {
                              selectedItemIndex = index
                        when (index) {
                            0 -> onHomeClick.invoke()
                            1 -> onAddClick.invoke()
                            2 -> onAccountClick.invoke()
                        }
                    },
                    label = {
                            Text(text = item.title)
                    },
                    alwaysShowLabel = false,
                    icon = { BadgedBox(
                        badge = {
                            if (item.badgeCount != null) {
                                Badge {
                                    Text(text = item.badgeCount.toString())
                                }
                            } else if (item.hasNews){
                                Badge ()
                            }
                        }
                    ) {
                        Icon(imageVector = if (index == selectedItemIndex){
                                              item.selectedIcon
                                          }else item.unSelectedIcon,
                            contentDescription = item.title)
                    } })
            }
        }
}

@Preview
@Composable
fun BottomAppBarPreview(){
    SCDPredictTheme {
        BottomAppBar(
            onHomeClick = {},
            onAddClick = {},
            onAccountClick = {}
        )
    }
}

@Preview
@Composable
fun BackNavigateTopAppBarPreview(){
    SCDPredictTheme {
        BackNavigateTopAppBar(title = "New",
            navController = rememberNavController())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TopAppBarPreview(){
    SCDPredictTheme {
        TopAppBar(
            userName = "Sharon",
            scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
            logoutOnClick = {}
        )
    }
}

