package br.com.challenge.application.city

import io.ktor.application.call
import io.ktor.routing.Routing
import io.ktor.routing.get

fun Routing.cityRoutes(cityController: CityController) {

    get("/cities/{name}") { cityController.playlist(call) }

}