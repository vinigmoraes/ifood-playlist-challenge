package unit.core.city

import br.com.challenge.core.city.CityService
import br.com.challenge.core.weather.domain.Warm
import br.com.challenge.core.weather.playlist.Playlist
import br.com.challenge.core.weather.playlist.ports.SpotifyGateway
import br.com.challenge.core.weather.ports.OpenWeatherGateway
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

class CityServiceTest {

    private val spotifyGateway = mockk<SpotifyGateway>()
    private val openWeatherGateway = mockk<OpenWeatherGateway>()
    private val cityService = CityService(openWeatherGateway, spotifyGateway)

    @Test
    fun `should return playlist successfully`() {
        val cityName = "campinas"
        val temperature = 21.0
        val warmWeather = Warm(temperature)
        val tracks = listOf("Music 1", "Music 2")
        val playlist = Playlist(name = "Pop music", tracks = tracks)

        every { openWeatherGateway.getCityTemperature(cityName) } returns temperature
        every { spotifyGateway.playlist(warmWeather.playlistType()).playlist } returns playlist

        val cityPlaylist = cityService.playlist(cityName)

        Assert.assertEquals(cityPlaylist.name, playlist.name)
        Assert.assertEquals(cityPlaylist.tracks, playlist.tracks)
    }

    @Test
    fun `should return temperature successfully`() {
        val cityName = "campinas"
        val expectedTemperature = 21.0

        every { openWeatherGateway.getCityTemperature(cityName) } returns expectedTemperature

        val temperature = cityService.getTemperature(cityName)

        Assert.assertEquals(expectedTemperature, temperature, 1.0)
    }
}