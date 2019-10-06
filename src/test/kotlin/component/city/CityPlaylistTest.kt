package component.city

import br.com.challenge.application.config.objectMapper
import br.com.challenge.application.main
import br.com.challenge.application.city.response.CityPlaylistResponse
import com.fasterxml.jackson.module.kotlin.readValue
import io.ktor.application.Application
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication
import org.junit.Assert
import org.junit.Test
import utils.readJsonResponse

class CityPlaylistTest {

    @Test
    fun `should return http status code 200 and a playlist of a city`() =
        withTestApplication(Application::main) {
            val cityName = "campinas"

            val response = handleRequest(HttpMethod.Get, "cities/$cityName/playlist") {
                addHeader("Content-Type", "application/json")
            }.response

            val playlist = objectMapper.readValue<CityPlaylistResponse>(response.content!!)

            Assert.assertEquals(playlist.cityName, cityName)
            Assert.assertNotNull(playlist.temperature)
            Assert.assertNotNull(playlist.playlist)
            Assert.assertEquals(HttpStatusCode.OK, response.status())
        }

    @Test
    fun `should return http status code 404 when city name doesn't exist`() =
        withTestApplication(Application::main) {
            val cityName = "test"
            val expectedResponse = readJsonResponse("city_not_found")

            val response = handleRequest(HttpMethod.Get, "cities/$cityName/playlist") {
                addHeader("Content-Type", "application/json")
            }.response

            Assert.assertEquals(HttpStatusCode.NotFound, response.status())
            Assert.assertEquals(expectedResponse, response.content)
        }
}