package br.com.challenge.core.city

import br.com.challenge.core.weather.WeatherFactory
import br.com.challenge.core.weather.playlist.Playlist
import br.com.challenge.core.weather.playlist.ports.SpotifyGateway
import br.com.challenge.core.weather.ports.OpenWeatherGateway

class CityService(
    private val openWeatherGateway: OpenWeatherGateway,
    private val spotifyGateway: SpotifyGateway
) {

    fun playlist(cityName: String): Playlist {
        val temperature = getTemperature(cityName)

        val weather = WeatherFactory.createWeather(temperature)

        return spotifyGateway.playlist(weather.playlistType()).playlist
    }

    fun getTemperature(cityName: String) = openWeatherGateway.getCityTemperature(cityName)
}