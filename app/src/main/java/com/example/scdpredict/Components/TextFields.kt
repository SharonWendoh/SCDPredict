package com.example.scdpredict.Components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.scdpredict.R
import com.example.scdpredict.ui.theme.SCDPredictTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoundedTextField(
    modifier: Modifier,
    placeholder: String,
){
    var text by remember { mutableStateOf(TextFieldValue("")) }
    TextField(
        value = text,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.onBackground,
            textColor = MaterialTheme.colorScheme.primary
        ),
        onValueChange = {newText ->
            text = newText
        },
        shape = RoundedCornerShape(20.dp),
        modifier =Modifier
            .border(BorderStroke(2.dp, Color.Black), shape = RoundedCornerShape(20.dp))
            .then(modifier),
        placeholder = {
            Row(
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_person_24),
                    contentDescription = "",
                    modifier = Modifier
                        .size(20.dp)
                )
                Spacer(modifier = Modifier.size(10.dp))
                Text (
                    text = placeholder,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyLarge

                )
            }

        },
    )
}

@Preview
@Composable
fun RoundedTextFieldPreview(){
    SCDPredictTheme() {
        RoundedTextField(modifier = Modifier,
            placeholder = "example@gmail.com" )
    }
}