package utils

import org.mockserver.integration.ClientAndServer
import org.mockserver.model.HttpRequest
import org.mockserver.model.HttpResponse

object MockServer {

    private const val apiKey = "b77e07f479efe92156376a8b07640ced"
    private const val latitude = 50f
    private const val longitude = 30f

    private lateinit var mockServer: ClientAndServer

    fun start() {
        mockServer = ClientAndServer.startClientAndServer(1090)
        createMocks()
    }

    fun stop() = mockServer.stop()

    private fun createMocks() {
        val response = readJsonResponse("openweather_city_temperature")

        mockServer.`when`(
            HttpRequest.request()
                .withMethod("GET")
                .withPath("/weather")
                .withQueryStringParameter("q", "Campinas")
                .withQueryStringParameter("units", "metric")
                .withQueryStringParameter("appid", apiKey)
        ).respond(
            HttpResponse.response()
                .withStatusCode(200)
                .withBody(response)
        )

        mockServer.`when`(
            HttpRequest.request()
                .withMethod("GET")
                .withPath("/weather")
                .withQueryStringParameter("q", "test")
                .withQueryStringParameter("units", "metric")
                .withQueryStringParameter("appid", apiKey)
        ).respond(
            HttpResponse.response()
                .withStatusCode(404)
                .withBody(response)
        )

        mockServer.`when`(
            HttpRequest.request()
                .withMethod("GET")
                .withPath("/weather")
                .withQueryStringParameter("lat", latitude.toString())
                .withQueryStringParameter("lon", longitude.toString())
                .withQueryStringParameter("units", "metric")
                .withQueryStringParameter("appid", apiKey)
        ).respond(
            HttpResponse.response()
                .withStatusCode(200)
                .withBody(response)
        )
    }
}