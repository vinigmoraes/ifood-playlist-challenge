package unit.core.city

import br.com.challenge.commons.exceptions.city.CityNotFoundException
import br.com.challenge.core.city.CityService
import br.com.challenge.core.weather.domain.Warm
import br.com.challenge.core.weather.playlist.Playlist
import br.com.challenge.core.weather.playlist.ports.SpotifyGateway
import br.com.challenge.core.weather.ports.OpenWeatherGateway
import br.com.challenge.infrastructure.gateway.openweather.response.OpenWeatherMainResponse
import br.com.challenge.infrastructure.gateway.openweather.response.OpenWeatherResponse
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

class CityServiceTest {

    private val spotifyGateway = mockk<SpotifyGateway>()
    private val openWeatherGateway = mockk<OpenWeatherGateway>()
    private val cityService = CityService(openWeatherGateway, spotifyGateway)

    @Test
    fun `should return spotify playlist successfully given city name`() {
        val cityName = "campinas"
        val temperature = 21.0
        val warmWeather = Warm(temperature)
        val tracks = listOf("Music 1", "Music 2")
        val playlist = Playlist(name = "Pop music", tracks = tracks)
        val city = OpenWeatherResponse(
            name = cityName,
            main = OpenWeatherMainResponse(temperature)
        )

        every { openWeatherGateway.getCityTemperature(cityName) } returns city
        every { spotifyGateway.playlist(warmWeather.playlistType()).playlist } returns playlist

        val cityPlaylist = cityService.playlist(cityName)

        Assert.assertEquals(cityPlaylist.playlist.name, playlist.name)
        Assert.assertEquals(cityPlaylist.playlist.tracks, playlist.tracks)
    }

    @Test
    fun `should return spotify playlist successfully given city coordinates`() {
        val cityName = "campinas"
        val latitude = -21.0f
        val longitude = 5f
        val temperature = 21.0
        val warmWeather = Warm(temperature)
        val tracks = listOf("Music 1", "Music 2")
        val playlist = Playlist(name = "Pop music", tracks = tracks)
        val city = OpenWeatherResponse(
            name = cityName,
            main = OpenWeatherMainResponse(temperature)
        )

        every { openWeatherGateway.getCityTemperature(latitude, longitude) } returns city
        every { spotifyGateway.playlist(warmWeather.playlistType()).playlist } returns playlist

        val cityPlaylist = cityService.playlist(latitude, longitude)

        Assert.assertEquals(cityPlaylist.playlist.name, playlist.name)
        Assert.assertEquals(cityPlaylist.playlist.tracks, playlist.tracks)
    }

    @Test(expected = CityNotFoundException::class)
    fun `should throw exception when city name was not found`() {
        val cityName = "campinas"
        val temperature = 21.0
        val warmWeather = Warm(temperature)
        val tracks = listOf("Music 1", "Music 2")
        val playlist = Playlist(name = "Pop music", tracks = tracks)

        every { openWeatherGateway.getCityTemperature(cityName) } returns null
        every { spotifyGateway.playlist(warmWeather.playlistType()).playlist } returns playlist

        cityService.playlist(cityName)
    }
}