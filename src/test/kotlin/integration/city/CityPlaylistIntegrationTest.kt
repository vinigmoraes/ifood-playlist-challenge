package integration.city

import br.com.challenge.application.city.response.CityPlaylistResponse
import br.com.challenge.application.config.objectMapper
import br.com.challenge.application.main
import com.fasterxml.jackson.module.kotlin.readValue
import integration.mainTest
import io.ktor.application.Application
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication
import org.junit.AfterClass
import org.junit.Assert
import org.junit.BeforeClass
import org.junit.Test
import utils.MockServer
import utils.readJsonResponse

class CityPlaylistIntegrationTest {

    companion object {
        @BeforeClass
        @JvmStatic
        fun setUp() {
            MockServer.start()
        }

        @AfterClass
        @JvmStatic
        fun setDown() {
            MockServer.stop()
        }
    }

    @Test
    fun `should return http status code 200 and a playlist of a city given city name`() =
        withTestApplication(Application::mainTest) {

            val cityName = "Campinas"

            val response = handleRequest(HttpMethod.Get, "cities/$cityName/playlist") {
                addHeader("Content-Type", "application/json")
            }.response

            val cityPlaylist = objectMapper.readValue<CityPlaylistResponse>(response.content!!)

            Assert.assertEquals(cityPlaylist.city.name, cityName)
            Assert.assertNotNull(cityPlaylist.city.temperature)
            Assert.assertNotNull(cityPlaylist.playlist)
            Assert.assertEquals(HttpStatusCode.OK, response.status())
        }

    @Test
    fun `should return http status code 404 when city name doesn't exist`() =
        withTestApplication(Application::mainTest) {
            val cityName = "test"
            val expectedResponse = readJsonResponse("city_not_found")

            val response = handleRequest(HttpMethod.Get, "cities/$cityName/playlist") {
                addHeader("Content-Type", "application/json")
            }.response

            Assert.assertEquals(HttpStatusCode.NotFound, response.status())
            Assert.assertEquals(expectedResponse, response.content)
        }

    @Test
    fun `should return http status code 200 and playlist of a city given city coordinates`() =
        withTestApplication(Application::mainTest) {
            val latitude = -22.9064
            val longitude = -47.0616

            val response = handleRequest(HttpMethod.Get, "cities/latitude/$latitude/longitude/$longitude/playlist") {
                addHeader("Content-Type", "application/json")
            }.response

            val cityPlaylist = objectMapper.readValue<CityPlaylistResponse>(response.content!!)

            Assert.assertNotNull(cityPlaylist.city.temperature)
            Assert.assertNotNull(cityPlaylist.playlist)
            Assert.assertEquals(HttpStatusCode.OK, response.status())
        }

    @Test
    fun `should return http status code 404 when city coordinates doesn't exist`() =
        withTestApplication(Application::main) {
            val latitude = -22.9064
            val longitude = "xxxx"

            val expectedResponse = readJsonResponse("invalid_parameter")

            val response = handleRequest(HttpMethod.Get, "cities/latitude/$latitude/longitude/$longitude/playlist") {
                addHeader("Content-Type", "application/json")
            }.response

            Assert.assertEquals(HttpStatusCode.UnprocessableEntity, response.status())
            Assert.assertEquals(expectedResponse, response.content)
        }
}