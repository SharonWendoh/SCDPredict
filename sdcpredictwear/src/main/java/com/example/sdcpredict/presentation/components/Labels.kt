package com.example.sdcpredict.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.health.services.client.data.DataTypeAvailability
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.example.sdcpredict.R
import com.example.sdcpredict.presentation.theme.SCDPredictTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@Composable
fun HeartRateLabel(
    hr: Double,
    availability: DataTypeAvailability
){
    val icon = when (availability) {
        DataTypeAvailability.AVAILABLE -> painterResource(id = R.drawable.heart_pulse)
        DataTypeAvailability.ACQUIRING -> painterResource(id = R.drawable.pulse)
        DataTypeAvailability.UNAVAILABLE,
        DataTypeAvailability.UNAVAILABLE_DEVICE_OFF_BODY -> painterResource(id = R.drawable.heart_broken)
        else -> painterResource(id = R.drawable.question_mark)
    }
    val text =
        if (availability == DataTypeAvailability.AVAILABLE) {
        hr.toInt().toString()
        }else {
                stringResource(id = R.string.no_hr_reading)
        }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column (
            verticalArrangement = Arrangement.Center
        ){
            Icon(
                painter = icon,
                contentDescription = stringResource(R.string.icon),
                modifier = Modifier
                    .size(60.dp)
                    .padding(15.dp, 0.dp),
                tint = Color.Red
            )
        }
        Column {
            Text(
                text = text,
                style = MaterialTheme.typography.display1
            )
        }
    }
}

@ExperimentalPermissionsApi
@Preview(
    device = Devices.WEAR_OS_SMALL_ROUND,
    showBackground = false,
    showSystemUi = true
)
@Composable
fun HrLabelPreview() {
    SCDPredictTheme {
        HeartRateLabel(
            hr = 121.2,
            availability = DataTypeAvailability.AVAILABLE
        )
    }
}