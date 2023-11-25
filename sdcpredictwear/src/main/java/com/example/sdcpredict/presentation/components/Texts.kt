package com.example.sdcpredict.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.example.sdcpredict.presentation.theme.SCDPredictTheme

@Composable
fun TitleText(
    modifier: Modifier,
    text: String
){
    Text(text = text,
        style = MaterialTheme.typography.title1,
        color = MaterialTheme.colors.primary,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .then(modifier)
    )
}

@Composable
fun NormalText(
    modifier: Modifier,
    text: String
){
    Text(text = text,
        style = MaterialTheme.typography.body2,
        color = MaterialTheme.colors.primary,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .then(modifier)
    )
}
@Composable
fun NumberText(
    modifier: Modifier,
    number: Int
){
    Text(text = number.toString(),
        style = MaterialTheme.typography.display1,
        color = MaterialTheme.colors.primary,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .then(modifier)
    )
}

@Preview
@Composable
fun TitleTextPreview(){
    SCDPredictTheme {
        TitleText(modifier = Modifier,
            text = "Heart Rate" )
    }
}

@Preview
@Composable
fun NormalTextPreview(){
    SCDPredictTheme {
        NormalText(
            modifier = Modifier,
            text = "Heart Rate" )
    }
}
@Preview
@Composable
fun NumberTextPreview(){
    SCDPredictTheme {
        NumberText(modifier = Modifier, 88)
    }
}