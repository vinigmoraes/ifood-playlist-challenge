package br.com.challenge.application.city.response

import br.com.challenge.core.city.City
import br.com.challenge.core.weather.playlist.Playlist

class CityPlaylistResponse(
    val city: City,
    val playlist: Playlist
)
