package com.example.weatherapp.domain.util


sealed class AppResult<T>(val data: T? = null, val message: String? = null) {


    class Done<T>(data: T?) : AppResult<T>(data)
    class Error<T>(message: String, data: T? = null) : AppResult<T>(data, message)

}