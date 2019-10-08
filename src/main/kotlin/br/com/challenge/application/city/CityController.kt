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

        val cityName = call.parameters["name"]!!

        val cityPlaylist = cityService.playlist(cityName)

        call.respond(HttpStatusCode.OK, CityPlaylistResponse(cityPlaylist.city, cityPlaylist.playlist))
    }

    suspend fun playlistByCoordinates(call: ApplicationCall) {

        val latitude = call.parameters["latitude"]?.toFloatOrNull() ?: throw InvalidParameter(call.parameters["latitude"])

        val longitude = call.parameters["longitude"]?.toFloatOrNull() ?: throw InvalidParameter(call.parameters["longitude"])

        val cityPlaylist = cityService.playlist(latitude, longitude)

        call.respond(HttpStatusCode.OK, CityPlaylistResponse(cityPlaylist.city, cityPlaylist.playlist))
    }
}