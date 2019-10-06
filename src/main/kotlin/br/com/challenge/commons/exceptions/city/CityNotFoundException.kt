package br.com.challenge.commons.exceptions.city

import br.com.challenge.commons.exceptions.CityPlaylistApiException
import br.com.challenge.commons.exceptions.errors.ErrorResponse
import io.ktor.http.HttpStatusCode

class CityNotFoundException(
    override val message: String,
    private val parameter: List<String>
) : CityPlaylistApiException() {

    override fun statusCode(): HttpStatusCode = HttpStatusCode.NotFound

    override fun response(): ErrorResponse = ErrorResponse.create(message, "parameter" to parameter)
}