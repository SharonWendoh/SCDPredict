package com.example.scdpredict.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.scdpredict.R
import com.example.scdpredict.ui.theme.SCDPredictTheme

@Composable
fun ScoreCardView(
    score: Int,
    title: String,
    text: String
){
    Card (
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(100.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onBackground
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp,
        ),
        border = null
    ){
    Row (
        modifier = Modifier
            .clickable(onClick = {})
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Box(
                modifier = Modifier
                    .padding(10.dp),){
                    MediumNumberText(
                        modifier = Modifier,
                        text = score.toString() )
            }
        }
        Column {
            Row {
                TitleText(modifier = Modifier,
                    text = title )
            }
            Spacer(modifier = Modifier.size(5.dp))

            Row {
                NormalText(modifier = Modifier,
                    text = text )
            }
        }
    }
    }
}

@Composable
fun MetricsCardView(
    score: Int,
    title: String,
    text: String,
    icon: Painter
){
    Card (
        modifier = Modifier
            .padding(8.dp)
            .size(130.dp, 180.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onBackground
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp,
        ),
        border = null
    ){
        Row (
            modifier =  Modifier.padding(10.dp,0.dp)
        ) {
            NormalText(modifier = Modifier,
                text = title )
        }
        Spacer(modifier = Modifier.size(15.dp))

        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier =  Modifier.padding(10.dp,0.dp)
        ) {
            Image(
                painter = icon,
                contentDescription = "" ,
                modifier = Modifier
                    .size(90.dp)
                    .padding(15.dp, 0.dp)
                   // .align(Alignment.CenterHorizontally)
            )
        }
        Spacer(modifier = Modifier.size(15.dp))

        Row (
            modifier =  Modifier.padding(10.dp,0.dp)
        ){
            Column {
                MediumNumberText(
                    modifier = Modifier,
                    text = score.toString() )
            }
            Column (
                verticalArrangement = Arrangement.Bottom
            ){
                NormalText(modifier = Modifier,
                    text = text )
            }
        }
    }
}

@Composable
fun TrackerCardView(
    icon: Painter,
    title: String,
    text: String
){
    Card (
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(80.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onBackground
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp,
        ),
        border = null
    ){
        Row (
            modifier = Modifier
                .clickable(onClick = {})
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Image(
                    painter = icon,
                    contentDescription = "" ,
                    modifier = Modifier
                        .size(70.dp)
                        .padding(15.dp, 0.dp)
                    // .align(Alignment.CenterHorizontally)
                )
            }
            Column {
                Row {
                    TitleText(modifier = Modifier,
                        text = title )
                }
                Spacer(modifier = Modifier.size(5.dp))

                Row {
                    NormalText(modifier = Modifier,
                        text = text )
                }
            }
        }
    }
}
@Preview
@Composable
fun ScoreCardViewPreview(){
    SCDPredictTheme {
        ScoreCardView(
            88,
            "Wellness Score",
            "Based on your data, we think your health status is above average")
    }
}
@Preview
@Composable
fun MetricsCardViewPreview(){
    SCDPredictTheme {
        MetricsCardView(
            88,
            "Heart Rate",
            "BPM",
            icon = painterResource(id = R.drawable.pulse))
    }
}

@Preview
@Composable
fun TrackerCardViewPreview(){
    SCDPredictTheme {
        TrackerCardView(
            painterResource(id = R.drawable.graph),
            "Medication",
            "Up to date")
    }
}