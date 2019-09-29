package br.com.challenge.infrastructure.gateway.openweather.exceptions

import br.com.challenge.infrastructure.gateway.exceptions.GatewayException
import br.com.challenge.infrastructure.gateway.openweather.exceptions.errors.ErrorResponse
import io.ktor.http.HttpStatusCode

class CityNotFoundException(
    override val message: String,
    val parameter: String
) : GatewayException() {

    override fun statusCode(): HttpStatusCode = HttpStatusCode.NotFound

    override fun response(): ErrorResponse = ErrorResponse.create(message, "parameter" to parameter)
}