package com.example.scdpredict.Components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.scdpredict.ui.theme.SCDPredictTheme

@Composable
fun NormalText(
    modifier: Modifier,
    text: String
){
    Text(text = text,
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .then(modifier)
    )

}

@Composable
fun TitleText(
    modifier: Modifier,
    text: String
){
    Text(text = text,
    style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .then(modifier)
        )
}

@Composable
fun TextFieldLabel(
    modifier: Modifier,
   text: String
){
    Text(text = text,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .then(modifier)
    )
}

@Composable
fun MediumNumberText(
    modifier: Modifier,
    text: String
){
    Text(text = text,
        style = MaterialTheme.typography.displayMedium,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .then(modifier)
    )
}

@Preview
@Composable
fun NormalTextPreview(){
    SCDPredictTheme() {
        NormalText(
            modifier = Modifier,
            text = "Sharon Wendoh"
        )
    }
}

@Preview
@Composable
fun TitleTextPreview(){
    SCDPredictTheme() {
        TitleText(
            modifier = Modifier,
            text = "Sharon Wendoh"
        )
    }
}
@Preview
@Composable
fun TextFieldLabelPreview(){
    SCDPredictTheme() {
        TextFieldLabel(
            modifier = Modifier,
            text = "Sharon Wendoh"
        )
    }
}
@Preview
@Composable
fun MediumNumberTextPreview(){
    SCDPredictTheme() {
        MediumNumberText(
            modifier = Modifier,
            text = "88"
        )
    }
}