package br.com.challenge.application.city

import io.ktor.application.call
import io.ktor.routing.Routing
import io.ktor.routing.get

fun Routing.cityRoutes(cityController: CityController) {

    get("/cities/{name}/playlist") { cityController.playlistByName(call) }

    get("/cities/latitude/{latitude}/longitude/{longitude}") {cityController.playlistByCoordinates(call)}

}