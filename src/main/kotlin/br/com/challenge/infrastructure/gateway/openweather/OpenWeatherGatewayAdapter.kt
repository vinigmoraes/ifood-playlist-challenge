package br.com.challenge.infrastructure.gateway.openweather

import br.com.challenge.application.config.httpClient
import br.com.challenge.commons.exceptions.city.CityNotFoundException
import br.com.challenge.core.weather.ports.OpenWeatherGateway
import br.com.challenge.infrastructure.gateway.openweather.response.OpenWeatherResponse
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.kittinunf.fuel.httpGet
import io.ktor.http.HttpStatusCode

class OpenWeatherGatewayAdapter(
    private val apiKey: String,
    private val url: String,
    private val objectMapper: ObjectMapper
) : OpenWeatherGateway {

    override fun getCityTemperature(latitude: Double, longitude: Double): OpenWeatherResponse? {

        val request = url(latitude, longitude).httpGet()

        val response = httpClient.executeRequest(request)

        if (response.statusCode == HttpStatusCode.NotFound.value) {
            throw CityNotFoundException(
                "Invalid coordinates",
                listOf(latitude.toString(), longitude.toString())
            )
        }

        return objectMapper.readValue(response.data, OpenWeatherResponse::class.java)
    }

    override fun getCityTemperature(cityName: String): OpenWeatherResponse? {

        val request = url(cityName).httpGet()

        val response = httpClient.executeRequest(request)

        if (response.statusCode == HttpStatusCode.NotFound.value) {
            return null
        }

        return objectMapper.readValue(response.data, OpenWeatherResponse::class.java)
    }

    private fun url(cityName: String) = "$url?q=$cityName&units=metric&appid=$apiKey"

    private fun url(latitude: Double, longitude: Double) =
        "$url?lat=$latitude&lon=$longitude&units=metric&appid=$apiKey"
}