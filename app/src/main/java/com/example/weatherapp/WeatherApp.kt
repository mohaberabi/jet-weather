package com.example.weatherapp

import android.app.Application
import com.example.weatherapp.core.di.AppModule
import com.example.weatherapp.core.di.AppModuleImpl


class WeatherApp : Application() {

    companion object {
        lateinit var appGraph: AppModule

    }


    override fun onCreate() {
        super.onCreate()
        appGraph = AppModuleImpl(this)
    }
}