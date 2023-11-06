package com.example.sdcpredict.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.MaterialTheme
import com.example.sdcpredict.R
import com.example.sdcpredict.presentation.Greeting
import com.example.sdcpredict.presentation.components.NormalText
import com.example.sdcpredict.presentation.components.NumberText
import com.example.sdcpredict.presentation.components.TitleText
import com.example.sdcpredict.presentation.theme.SCDPredictTheme

@Composable
fun MeasuredPage(
    icon : Painter
){
    SCDPredictTheme {
        /* If you have enough items in your list, use [ScalingLazyColumn] which is an optimized
         * version of LazyColumn for wear devices with some added features. For more information,
         * see d.android.com/wear/compose.
         */
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TitleText(modifier = Modifier,
                text = "Heart Rate" )
            NormalText(modifier = Modifier,
                text = "Status : Available" )
            NormalText(modifier = Modifier,
                text = "Last Measure" )
            Row {
                Column {
                    Image(
                        painter = icon,
                        contentDescription = "" ,
                        modifier = Modifier
                            .size(60.dp)
                            .padding(10.dp, 0.dp)
                        // .align(Alignment.CenterHorizontally)
                    )
                }
                Column {
                    Row {
                        NumberText(modifier = Modifier, 88)
                    }
                }
            }
        }
    }
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun MeasuredPagePreview(){
    SCDPredictTheme {
        MeasuredPage(icon = painterResource(id = R.drawable.heart_pulse))
    }
}