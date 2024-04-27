package com.example.weatherapp.domain.lcoation

import android.location.Location

interface LocationTracker {


    suspend fun getCurrentLocation(): Location?

    
}