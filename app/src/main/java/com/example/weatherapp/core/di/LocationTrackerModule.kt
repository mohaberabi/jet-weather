package com.example.weatherapp.core.di

import com.example.weatherapp.data.repository.LocationTrackerImpl
import com.example.weatherapp.domain.lcoation.LocationTracker
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module

@InstallIn(SingletonComponent::class)
abstract class LocationTrackerModule {


    @Binds
    @Singleton
    abstract fun bindLocationTracker(
        locationTrackerImpl: LocationTrackerImpl
    ): LocationTracker

    
}