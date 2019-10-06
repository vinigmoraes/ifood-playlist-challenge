package br.com.challenge.infrastructure.gateway.openweather

import br.com.challenge.application.config.httpClient
import br.com.challenge.commons.exceptions.city.CityNotFoundException
import br.com.challenge.core.weather.ports.OpenWeatherGateway
import br.com.challenge.infrastructure.gateway.openweather.response.OpenWeatherResponse
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.RequestFactory
import com.github.kittinunf.fuel.httpGet
import io.ktor.http.HttpStatusCode

class OpenWeatherGatewayAdapter(
    private val apiKey: String,
    private val url: String,
    private val objectMapper: ObjectMapper
) : OpenWeatherGateway {

    override fun getCityTemperature(cityName: String) = url(cityName)
        .httpGet(Parameters)


    }


    private fun url(cityName: String) = "$url?q=$cityName&units=metric&appid=$apiKey"

    Fuel.get(url(cityName))
    .response()
    .let {

        if(it.second.statusCode == HttpStatusCode.NotFound.value) {
            throw CityNotFoundException("City with name: $cityName was not found", cityName)
        }

        objectMapper.readValue(it.second.data, OpenWeatherResponse::class.java)
            .main
            .temp
    }


}