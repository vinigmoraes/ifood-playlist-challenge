package br.com.challenge.application.health

import br.com.challenge.application.health.response.HealthResponse
import io.ktor.application.ApplicationCall
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond

class HealthController {

    suspend fun health(call: ApplicationCall) {
        call.respond(HttpStatusCode.OK, HealthResponse(status = "UP"))
    }
}
