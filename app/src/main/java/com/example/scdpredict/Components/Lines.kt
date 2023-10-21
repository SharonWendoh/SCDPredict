package com.example.scdpredict.Components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.scdpredict.ui.theme.SCDPredictTheme

@Composable
fun HorizontalLineWithText(
    text: String,
    lineColor: Color,
    textColor: Color
){
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Divider(
                color = lineColor,
                modifier = Modifier
                    .size(150.dp, 2.dp)
            )
            Spacer(modifier = Modifier.size(5.dp))
            Text(
                text = text,
                color = textColor,
                textAlign = TextAlign.Center,
                //fontStyle = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(3.dp),
            )
            Divider(
                color = lineColor,
                thickness = 2.dp,
                modifier = Modifier
                    .size(150.dp, 2.dp)
            )
        }

}

@Preview
@Composable
fun HorizontalLineWithTextPreview(){
    SCDPredictTheme {
        HorizontalLineWithText(
            text = "or",
            lineColor = MaterialTheme.colorScheme.primary,
            textColor = MaterialTheme.colorScheme.primary)
    }
}