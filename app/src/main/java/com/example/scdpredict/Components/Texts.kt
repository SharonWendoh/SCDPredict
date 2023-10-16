package com.example.scdpredict.Components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.scdpredict.ui.theme.SCDPredictTheme

@Composable
fun normalText(
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

@Preview
@Composable
fun normalTextPreview(){
    SCDPredictTheme() {
        normalText(
            modifier = Modifier,
            text = "Sharon Wendoh"
        )
    }
}