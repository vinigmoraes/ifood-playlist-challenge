package br.com.challenge.infrastructure.gateway.openweather

import br.com.challenge.commons.exceptions.city.CityNotFoundException
import br.com.challenge.core.weather.ports.OpenWeatherGateway
import br.com.challenge.infrastructure.gateway.openweather.response.OpenWeatherResponse
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.isSuccessful

class OpenWeatherGatewayAdapter(
    private val apiKey: String,
    private val url: String,
    private val objectMapper: ObjectMapper
) : OpenWeatherGateway {

    override fun getCityTemperature(latitude: Float, longitude: Float): OpenWeatherResponse? {

        val response = Fuel.get(url(latitude, longitude))
            .responseString()

        if (!response.second.isSuccessful) {
            throw CityNotFoundException("Invalid coordinates", listOf(latitude.toString(), longitude.toString()))
        }

        return objectMapper.readValue(response.second.data, OpenWeatherResponse::class.java)
    }

    override fun getCityTemperature(cityName: String): OpenWeatherResponse? {

        val response = Fuel.get(url(cityName))
            .responseString()

        if (!response.second.isSuccessful) {
            throw CityNotFoundException("City with name: $cityName was not found", listOf(cityName))
        }

        return objectMapper.readValue(response.second.data, OpenWeatherResponse::class.java)
    }

    private fun url(cityName: String) = "$url?q=$cityName&units=metric&appid=$apiKey"

    private fun url(latitude: Float, longitude: Float) =
        "$url?lat=$latitude&lon=$longitude&units=metric&appid=$apiKey"
}