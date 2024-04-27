package com.example.weatherapp.domain.model

data class WeatherInfo(


    val weatherDataPerDay: Map<Int, List<WeatherDataModel>>,
    val currentWeatherData: WeatherDataModel?

)
