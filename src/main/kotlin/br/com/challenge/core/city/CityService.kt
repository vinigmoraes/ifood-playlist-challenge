package br.com.challenge.core.city

import br.com.challenge.commons.exceptions.city.CityNotFoundException
import br.com.challenge.core.weather.WeatherFactory
import br.com.challenge.core.weather.playlist.Playlist
import br.com.challenge.core.weather.playlist.ports.SpotifyGateway
import br.com.challenge.core.weather.ports.OpenWeatherGateway

class CityService(
    private val openWeatherGateway: OpenWeatherGateway,
    private val spotifyGateway: SpotifyGateway
) {

    fun playlist(cityName: String): CityPlaylist {
        val response = getTemperature(cityName) ?: throw CityNotFoundException(
            "City with name: $cityName was not found",
            listOf(cityName)
        )

        val weather = WeatherFactory.createWeather(response.main.temp)

        val playlist = spotifyGateway.playlist(weather.playlistType()).playlist

        val city = City.create(response.name, response.main.temp)

        return CityPlaylist(city, playlist)
    }

    fun playlist(latitude: Double, longitude: Double): CityPlaylist {
        val response = getTemperature(latitude, longitude) ?: throw CityNotFoundException(
            "City with coordinates: $latitude, $longitude not found",
            listOf(latitude.toString(), longitude.toString())
        )

        val weather = WeatherFactory.createWeather(response.main.temp)

        val playlist = spotifyGateway.playlist(weather.playlistType()).playlist

        val city = City.create(response.name, response.main.temp)

        return CityPlaylist(city, playlist)
    }

    private fun getTemperature(cityName: String) = openWeatherGateway.getCityTemperature(cityName)

    private fun getTemperature(latitude: Double, longitude: Double) = openWeatherGateway.getCityTemperature(latitude, longitude)
}