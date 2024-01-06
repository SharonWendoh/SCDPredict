package com.example.sdcpredict.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonColors
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.example.sdcpredict.R
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted

@Composable
fun NormalButton(
    modifier: Modifier,
    text: String,
    onButtonClick: () -> Unit
){
    Button(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .then(modifier),
        onClick =  onButtonClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background),
    ) {
        NormalText(
            modifier = Modifier, text = text )
    }
}
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
fun NormalButtonPreview(){
    NormalButton(modifier = Modifier, text = "Sign In") {
        
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