package com.example.sdcpredict.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text
import com.example.sdcpredict.R
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MeasureButton(
    permissionState: PermissionState,
    onButtonClick: () -> Unit,
    enabled: Boolean,
){
    Button(
        modifier = Modifier.fillMaxWidth(0.5f),
        onClick = {
            if (permissionState.status.isGranted) {
                onButtonClick()
            } else {
                permissionState.launchPermissionRequest()
            }
        }
    ) {
        val buttonTextId = if (enabled) {
            R.string.stop
        } else {
            R.string.start
        }
        Text(stringResource(buttonTextId))
    }
}

@ExperimentalPermissionsApi
@Preview(
    device = Devices.WEAR_OS_SMALL_ROUND,
    showBackground = false,
    showSystemUi = true
)
@Composable
fun MeasureButtonPreview(){
   // MeasureButton(permissionState = PermissionState, onButtonClick = { /*TODO*/ }, enabled = )
}