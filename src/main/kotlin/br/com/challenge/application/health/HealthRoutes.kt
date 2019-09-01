package br.com.challenge.application.health

import br.com.challenge.application.health.HealthController
import io.ktor.application.call
import io.ktor.routing.Routing
import io.ktor.routing.get

fun Routing.health(healthController: HealthController) {

    get("/health") { healthController.health(call) }
}