package br.com.challenge.core.weather.ports

import br.com.challenge.infrastructure.gateway.openweather.response.OpenWeatherResponse

interface OpenWeatherGateway {

    fun getCityTemperature(cityName: String): Double

}