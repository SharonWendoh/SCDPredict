package com.example.scdpredict.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.scdpredict.R
import com.example.scdpredict.ui.theme.SCDPredictTheme

@Composable
fun LargeTopBox(
    title: String,
    text: String
){
    Column (
        modifier = Modifier
            .fillMaxSize()
    ){
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.onBackground)
                .clip(RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp))
                .fillMaxWidth()
                .size(150.dp),
            contentAlignment = Alignment.Center
        ){
            Column (
                modifier = Modifier
                    .align(Alignment.Center)
            ){
                Row (
                    modifier = Modifier
                ){
                    Image(
                        painter = painterResource(id = R.drawable.plus),
                        contentDescription = "",
                        modifier = Modifier
                            .size(20.dp)

                    )
                }

                TitleText(
                    modifier = Modifier,
                    text = title)
                NormalText(
                    modifier = Modifier,
                    text = text )

            }

        }
    }

}

@Preview
@Composable
fun LargeTopBoxPreview(){
    SCDPredictTheme {
        LargeTopBox(
            title = "Sign In",
            text = "Sign In and get your SCD monitoring personalised")
    }
}