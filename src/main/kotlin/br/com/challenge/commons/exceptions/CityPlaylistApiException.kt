package br.com.challenge.commons.exceptions

import br.com.challenge.commons.exceptions.errors.ErrorResponse
import io.ktor.http.HttpStatusCode

abstract class CityPlaylistApiException : RuntimeException() {

    abstract fun response() : ErrorResponse

    abstract fun statusCode() : HttpStatusCode
}