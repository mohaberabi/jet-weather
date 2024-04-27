package com.example.weatherapp.data.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.weatherapp.data.remote.WeatherDataDto
import com.example.weatherapp.data.remote.WeatherDto
import com.example.weatherapp.domain.util.WeatherType
import com.example.weatherapp.domain.model.WeatherDataModel
import com.example.weatherapp.domain.model.WeatherInfo
import com.example.weatherapp.domain.model.WeatherModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


private data class IndexedWeatherData(
    val index: Int,
    val data: WeatherDataModel,
)

fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherDataModel>> {


    return time.mapIndexed { index, time ->


        val temp = temperatures[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeeds[index]
        val pressure = pressures[index]
        val humid = humidities[index]

        IndexedWeatherData(
            index, WeatherDataModel(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                tempCel = temp,
                pressures = pressure,
                humidity = humid,
                weatherType = WeatherType.fromWMO(weatherCode),
                windSpeeds = windSpeed,
            )
        )

    }.groupBy {


        it.index / 24
    }.mapValues {
        it.value.map { indexed ->
            indexed.data
        }
    }
}


fun WeatherDto.toWeatherInfo(): WeatherInfo {

    val weatherDataMap = weatherData.toWeatherDataMap()

    val now = LocalDateTime.now()
    val curr = weatherDataMap[0]?.find {
        val hour = if (now.minute < 30) now.hour else now.hour + 1
        it.time.hour == hour
    }
    return WeatherInfo(weatherDataMap, curr)
}