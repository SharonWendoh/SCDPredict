package com.example.scdpredict.Components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.scdpredict.R
import com.example.scdpredict.ui.theme.SCDPredictTheme

@Composable
fun SimpleSwitch(){
    var checked by remember { mutableStateOf(true) }

    Switch(
        checked = checked,
        onCheckedChange = {
            checked = it
        },
        modifier = Modifier
            .size(70.dp),
        thumbContent = {
            if (checked)
                {
                    Icon(
                        painter = painterResource(id = R.drawable.check) ,
                        contentDescription = "" ,
                        modifier = Modifier.size(SwitchDefaults.IconSize)
                    )

                } else {
                    null
                }
        }

    )
}

@Preview
@Composable
fun SimpleSwitchPreview(){
    SCDPredictTheme {
        SimpleSwitch()
    }
}