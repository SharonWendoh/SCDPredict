package com.example.scdpredict.Components

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
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

@Composable
fun LargeNumberText(
    modifier: Modifier,
    text: Int
){
    Text(text = text.toString(),
        style = MaterialTheme.typography.displayLarge,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .then(modifier)
    )
}
@Composable
fun LinkText(
    text: AnnotatedString,
    modifier: Modifier,
    onclick: (Int) -> Unit
)
{
    ClickableText(
        text = text,
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier
            .then(modifier),
        onClick = onclick)
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

@Preview
@Composable
fun LargeNumberTextPreview() {
    SCDPredictTheme() {
        LargeNumberText(
            modifier = Modifier,
            text = 88
        )
    }
}

@Preview
@Composable
fun LinkTextPreview() {
    SCDPredictTheme() {
        LinkText(
            modifier = Modifier,
            onclick = {},
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(
                    color = MaterialTheme.colorScheme.primary),
                ) {
                    append("Already have an account?")
                }
                withStyle(style = SpanStyle(
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary)
                ) {
                    append(" Sign in.")
                }
            }
        )
    }
}