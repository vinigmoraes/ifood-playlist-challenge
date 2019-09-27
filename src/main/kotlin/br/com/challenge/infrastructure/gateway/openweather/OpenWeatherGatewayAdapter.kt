package br.com.challenge.infrastructure.gateway.openweather

import br.com.challenge.core.weather.ports.OpenWeatherGateway
import br.com.challenge.infrastructure.gateway.openweather.response.OpenWeatherResponse
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.kittinunf.fuel.Fuel

class OpenWeatherGatewayAdapter(
    private val apiKey: String,
    private val url: String,
    private val objectMapper: ObjectMapper
) : OpenWeatherGateway {

    override fun getCityTemperature(cityName: String): Double =
        Fuel.get(url(cityName))
            .response()
            .let {
                val temperature = objectMapper.readValue(it.second.data, OpenWeatherResponse::class.java)
                temperature.main.temp
            }

    private fun url(cityName: String) = "$url?q=$cityName&units=metric&appid=$apiKey"
}