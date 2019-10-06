package br.com.challenge.application.city

import br.com.challenge.application.city.response.CityPlaylistResponse
import br.com.challenge.commons.exceptions.InvalidParameter
import br.com.challenge.core.city.CityService
import io.ktor.application.ApplicationCall
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond

class CityController(
    private val cityService: CityService
) {

    suspend fun playlistByName(call: ApplicationCall) {

        val cityName = call.parameters["name"] ?: throw InvalidParameter(call.parameters["name"])

        val cityPlaylist = cityService.playlist(cityName)

        call.respond(HttpStatusCode.OK, CityPlaylistResponse(cityPlaylist.city, cityPlaylist.playlist))
    }

    suspend fun playlistByCoordinates(call: ApplicationCall) {

        val latitude = call.parameters["latitude"]?.toDoubleOrNull() ?: throw Exception()

        val longitude = call.parameters["longitude"]?.toDoubleOrNull() ?: throw Exception()

        val cityPlaylist = cityService.playlist(latitude, longitude)

        call.respond(HttpStatusCode.OK, CityPlaylistResponse(cityPlaylist.city, cityPlaylist.playlist))
    }
}