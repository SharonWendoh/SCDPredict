package com.example.scdpredict.Components

import android.media.tv.TvContract.Channels.Logo
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.modifier.modifierLocalConsumer
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

        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.onBackground)
                .clip(RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp))
                .fillMaxWidth()
                .size(200.dp)
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ){
            Column (
                modifier = Modifier
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Row (
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically

                ){
                    Image(
                        painter = painterResource(id = R.drawable.plus),
                        contentDescription = "",
                        modifier = Modifier
                            .size(40.dp)

                    )
                }

                TitleText(
                    modifier = Modifier
                        ,
                    text = title)
                NormalText(
                    modifier = Modifier,
                    text = text )
            }
        }
}
@Composable
fun IconBox(
    logo: Painter,
    onClick: () -> Unit
){
    Box(modifier = Modifier
        .border(border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary))
        .clip(RoundedCornerShape(10.dp))
        .clickable { onClick ()}
    ){
        Image(
            painter = logo,
            contentDescription = "",
            modifier = Modifier
                .size(60.dp)
                .padding(5.dp)
                )
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
@Preview
@Composable
fun IconBoxPreview(){
    SCDPredictTheme {
        IconBox(
            logo = painterResource(id = R.drawable.google),
            onClick = {}
        )
    }
}