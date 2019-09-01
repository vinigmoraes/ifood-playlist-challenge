package br.com.challenge.application.health

import io.ktor.application.ApplicationCall
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond

class HealthController {

    suspend fun health(call: ApplicationCall) {
        call.respond(HttpStatusCode.OK)
    }
}
