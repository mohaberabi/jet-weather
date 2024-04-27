package com.example.weatherapp.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.presentation.compose.WeatherDataCompose
import com.example.weatherapp.presentation.viewmodel.WeatherState
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt


@Composable

fun WeatherScreen(
    state: WeatherState,
    color: androidx.compose.ui.graphics.Color,
    modifier: Modifier = Modifier,
) {

    if (state.isLoading) {


        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(color = Color.Green)
        }

    } else {
        state.info?.currentWeatherData?.let {

                data ->
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = color,

                    ),
                shape = RoundedCornerShape(10.dp),
                modifier = modifier.padding(16.dp)
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {


                    Text(
                        modifier = Modifier.align(Alignment.End),
                        text = "Today ${data.time.format(DateTimeFormatter.ofPattern("HH:mm"))}",
                        style = MaterialTheme.typography.bodyLarge.copy(color = androidx.compose.ui.graphics.Color.White)
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Image(
                        painter = painterResource(id = data.weatherType.iconRes),
                        contentDescription = null,
                        modifier = Modifier.width(200.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        modifier = Modifier.align(Alignment.End),
                        text = "Today ${data.tempCel} C",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = androidx.compose.ui.graphics.Color.White,
                            fontSize = 50.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        modifier = Modifier.align(Alignment.End),
                        text = "Today ${data.weatherType.weatherDesc} ",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = androidx.compose.ui.graphics.Color.White,
                            fontSize = 20.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row {

                        WeatherDataCompose(
                            value = data.pressures.roundToInt(),
                            unit = "hpa",
                            icon = ImageVector.vectorResource(
                                id = R.drawable.ic_pressure,

                                ),
                            iconTint = Color.White,
                            textStyle = TextStyle(color = Color.White)
                        )
                        WeatherDataCompose(
                            value = data.humidity.roundToInt(),
                            unit = "%",
                            icon = ImageVector.vectorResource(
                                id = R.drawable.ic_drop,

                                ),
                            iconTint = Color.White,
                            textStyle = TextStyle(color = Color.White)
                        )
                        WeatherDataCompose(
                            value = data.pressures.roundToInt(),
                            unit = "km/h",
                            icon = ImageVector.vectorResource(
                                id = R.drawable.ic_wind,
                            ),
                            iconTint = Color.White,
                            textStyle = TextStyle(color = Color.White)
                        )
                    }
                }

            }

        } ?: run {

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = if (state.error.isBlank()) "Error" else state.error)
            }


        }

    }


}