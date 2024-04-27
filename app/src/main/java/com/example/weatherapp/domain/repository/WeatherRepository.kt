package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.util.AppResult
import com.example.weatherapp.domain.model.WeatherInfo

interface WeatherRepository {


    suspend fun getWeatherInfo(lat: Double, lng: Double): AppResult<WeatherInfo>
}