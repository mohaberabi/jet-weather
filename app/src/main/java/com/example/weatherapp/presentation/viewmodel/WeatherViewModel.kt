package com.example.weatherapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.lcoation.LocationTracker
import com.example.weatherapp.domain.model.WeatherInfo
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.domain.util.AppResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class WeatherState(
    val error: String = "",
    val isLoading: Boolean = false,
    val info: WeatherInfo? = null
)


class WeatherViewModel(
    private val weatherRepository: WeatherRepository,
    private val locationTracker: LocationTracker,

    ) : ViewModel() {


    private val _state = MutableStateFlow(WeatherState())


    val state: StateFlow<WeatherState>
        get() = _state


    fun getWeatherInfo() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true,
                )
            }
            locationTracker.getCurrentLocation()?.let { loc ->
                when (val result = weatherRepository.getWeatherInfo(loc.latitude, loc.longitude)) {

                    is AppResult.Done -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                info = result.data
                            )
                        }
                    }

                    is AppResult.Error -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                error = result.message ?: "Something went  wrong "
                            )
                        }
                    }
                }
            } ?: run {

                _state.update {
                    it.copy(
                        error = "Location is Denied "
                    )
                }
            }

        }
    }
}




