package br.com.challenge.application.playlist

import br.com.challenge.core.weather.playlist.Playlist

class PlaylistResponse(
    val cityName: String,
    val temperature: Double,
    val playlist: Playlist
)
