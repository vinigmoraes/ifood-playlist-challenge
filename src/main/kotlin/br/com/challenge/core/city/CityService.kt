package br.com.challenge.core.city

import br.com.challenge.core.weather.WeatherFactory
import br.com.challenge.core.weather.domain.Weather
import br.com.challenge.core.weather.playlist.PlaylistSuggester
import br.com.challenge.core.weather.ports.OpenWeatherGateway

class CityService(
    private val openWeatherGateway: OpenWeatherGateway,
    private val playlistSuggester: PlaylistSuggester
) {

    fun playlist(cityName: String) {
        val temperature = openWeatherGateway.getCityTemperature(cityName)

        val weather = WeatherFactory.createWeather(temperature)

        playlistSuggester.preparePlaylist(weather)
    }
}