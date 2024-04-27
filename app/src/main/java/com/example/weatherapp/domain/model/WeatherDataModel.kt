package com.example.weatherapp.domain.model

import com.example.weatherapp.domain.util.WeatherType
import com.squareup.moshi.Json
import java.time.LocalDateTime

data class WeatherDataModel(
    val time: LocalDateTime,
    val tempCel: Double,
    val pressures: Double,
    val windSpeeds: Double,
    val humidity: Double,
    val weatherType: WeatherType,
)
