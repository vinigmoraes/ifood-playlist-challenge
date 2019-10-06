package br.com.challenge.application.city.response

import br.com.challenge.core.weather.playlist.Playlist

class CityPlaylistResponse(
    val cityName: String,
    val temperature: Double,
    val playlist: Playlist
)
