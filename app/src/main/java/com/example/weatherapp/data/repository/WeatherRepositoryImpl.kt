package com.example.weatherapp.data.repository

import com.example.weatherapp.data.mapper.toWeatherInfo
import com.example.weatherapp.data.remote.WeatherApi
import com.example.weatherapp.domain.util.AppResult
import com.example.weatherapp.domain.model.WeatherInfo
import com.example.weatherapp.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val api: WeatherApi
) : WeatherRepository {
    override suspend fun getWeatherInfo(lat: Double, lng: Double): AppResult<WeatherInfo> {
        return try {
            val res = api.getWeather(lat, lng).toWeatherInfo()
            return AppResult.Done(data = res)
        } catch (e: Exception) {
            e.printStackTrace()
            AppResult.Error(message = e.toString())
        }
    }
}