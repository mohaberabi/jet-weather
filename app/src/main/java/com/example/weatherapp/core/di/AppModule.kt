package com.example.weatherapp.core.di

import android.app.Application
import com.example.weatherapp.data.remote.WeatherApi
import com.example.weatherapp.data.repository.LocationTrackerImpl
import com.example.weatherapp.data.repository.WeatherRepositoryImpl
import com.example.weatherapp.domain.lcoation.LocationTracker
import com.example.weatherapp.domain.repository.WeatherRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton


interface AppModule {
    val weatherApi: WeatherApi
    val weatherRepository: WeatherRepository
    val locationTracker: LocationTracker
}


class AppModuleImpl(
    private val app: Application
) : AppModule {
    override val weatherApi: WeatherApi by lazy {
        Retrofit.Builder().baseUrl("https://api.open-meteo.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(WeatherApi::class.java)
    }


    override val weatherRepository: WeatherRepository
            by lazy {
                WeatherRepositoryImpl(weatherApi)
            }
    val locationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(app)
    }

    override val locationTracker: LocationTracker by lazy {
        LocationTrackerImpl(locationClient, app)
    }


}