package br.com.challenge.application.city

import br.com.challenge.application.city.response.CityPlaylistResponse
import br.com.challenge.commons.exceptions.InvalidCityNameParameter
import br.com.challenge.core.city.CityService
import io.ktor.application.ApplicationCall
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond

class CityController(
    private val cityService: CityService
) {

    suspend fun playlist(call: ApplicationCall) {

        val cityName = call.parameters["name"] ?: throw InvalidCityNameParameter(call.parameters["name"])

        val playlist = cityService.playlist(cityName)

        val temperature = cityService.getTemperature(cityName)

        call.respond(HttpStatusCode.OK, CityPlaylistResponse(cityName, temperature, playlist))
    }
}