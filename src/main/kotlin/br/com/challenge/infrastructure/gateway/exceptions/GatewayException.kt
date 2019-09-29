package br.com.challenge.infrastructure.gateway.exceptions

import br.com.challenge.infrastructure.gateway.openweather.exceptions.errors.ErrorResponse
import io.ktor.http.HttpStatusCode
import java.lang.RuntimeException

abstract class GatewayException : RuntimeException() {

    abstract fun response() : ErrorResponse

    abstract fun statusCode() : HttpStatusCode
}