package utils

import org.mockserver.integration.ClientAndServer
import org.mockserver.model.HttpRequest
import org.mockserver.model.HttpResponse

object MockServer {

    private val mockServer: ClientAndServer = ClientAndServer.startClientAndServer(1090)

    fun start() {
        val apiKey = "b77e07f479efe92156376a8b07640ced"
        val latitude = 50f
        val longitude = 30f

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

    fun stop() = mockServer.stop()
}