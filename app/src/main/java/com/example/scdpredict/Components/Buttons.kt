package com.example.scdpredict.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scdpredict.ui.theme.SCDPredictTheme

@Composable
fun Button(
    text:String,
    modifier: Modifier,
    onclick : () -> Unit
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(CircleShape)
            .background(color = MaterialTheme.colorScheme.secondary)
            .width(280.dp)
            .height(55.dp)
            .then(modifier)
            .clickable { onclick() } // Make the box clickable
    ){
        Text(
            text = text,
            modifier = Modifier,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun ButtonWithRoundedCorner(
    text: String,
    modifier: Modifier
){
        Button(onClick = { /*TODO*/ },
        shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .size(50.dp)
        ) {

            Text(text = text)

        }
}

@Preview
@Composable
fun ButtonWithRoundedCornerPreview(){
              SCDPredictTheme {
                  ButtonWithRoundedCorner(
                      "Rounded Button",
                      modifier = Modifier)

              }
}
@Preview
@Composable
fun buttonPreview(){
    SCDPredictTheme() {
        Row(

        ){
            Button(
                text = "login",
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape),
                onclick = {}
            )
        }
    }
}