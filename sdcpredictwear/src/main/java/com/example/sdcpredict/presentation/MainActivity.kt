/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.example.sdcpredict.presentation

import android.os.Bundle
import android.view.inputmethod.InputBinding
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.health.services.client.HealthServices
import androidx.health.services.client.data.DataType
import androidx.lifecycle.lifecycleScope
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.example.sdcpredict.R
import com.example.sdcpredict.presentation.screens.MeasuredPage
import com.example.sdcpredict.presentation.theme.SCDPredictTheme
import kotlinx.coroutines.launch
import kotlinx.coroutines.*

class MainActivity : ComponentActivity() {
    //private lateinit var binding: ActivityMainBinding

    var supportsHeartRate: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val healthClient = HealthServices.getClient(this /*context*/)
//        val measureClient = healthClient.measureClient
//        suspend fun fetchData() {
//            val capabilities = measureClient.getCapabilitiesAsync().await()
//            supportsHeartRate = DataType.HEART_RATE_BPM in capabilities.supportedDataTypesMeasure
//        }
//
//        lifecycleScope.launch {
//            fetchData()
//        }

        setContent {
            MeasuredPage(icon = painterResource(id = R.drawable.heart_pulse))

        }
    }
}

@Composable
fun WearApp(greetingName: String) {
    SCDPredictTheme {
        /* If you have enough items in your list, use [ScalingLazyColumn] which is an optimized
         * version of LazyColumn for wear devices with some added features. For more information,
         * see d.android.com/wear/compose.
         */
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            verticalArrangement = Arrangement.Center
        ) {
            Greeting(greetingName = greetingName)
        }
    }
}

@Composable
fun Greeting(greetingName: String) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.primary,
        text = stringResource(R.string.hello_world, greetingName)
    )
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    MeasuredPage(icon = painterResource(id = R.drawable.heart_pulse))
}