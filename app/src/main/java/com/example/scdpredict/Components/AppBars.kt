package com.example.scdpredict.Components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
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
import com.google.android.material.bottomappbar.BottomAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    scrollBehavior: TopAppBarScrollBehavior
){

    MediumTopAppBar(
        title = {
            Text(text = "Hello, Sharon")
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
            IconButton(onClick = { /*TODO*/ }) {
                Icon(painter = painterResource(id = R.drawable.settings),
                    contentDescription = "Settings" )
            }
        },
        scrollBehavior = scrollBehavior
        )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomAppBar(
    onNavigationItemClick: () -> Unit
){
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
                        onNavigationItemClick
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
            onNavigationItemClick = {}
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TopAppBarPreview(){
    SCDPredictTheme {
        TopAppBar(
            scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
        )
    }
}