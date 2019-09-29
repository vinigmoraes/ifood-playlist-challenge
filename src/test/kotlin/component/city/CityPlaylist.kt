package component.city

import br.com.challenge.application.config.objectMapper
import br.com.challenge.application.main
import br.com.challenge.application.playlist.PlaylistResponse
import br.com.challenge.core.weather.playlist.Playlist
import com.fasterxml.jackson.module.kotlin.readValue
import io.ktor.application.Application
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication
import org.junit.Assert
import org.junit.Test
import org.skyscreamer.jsonassert.JSONAssert

class CityPlaylist {

    @Test
    fun `should return http status code 200 and a playlist of a city`() =
        withTestApplication(Application::main) {
            val cityName = "campinas"

            val response = handleRequest(HttpMethod.Get, "cities/$cityName/playlist") {
                addHeader("Content-Type", "application/json")
            }.response

            val playlist = objectMapper.readValue<PlaylistResponse>(response.content!!)

            Assert.assertEquals(playlist.cityName, cityName)
            Assert.assertNotNull(playlist.temperature)
            Assert.assertNotNull(playlist.playlist)
            Assert.assertEquals(HttpStatusCode.OK, response.status())
        }
}