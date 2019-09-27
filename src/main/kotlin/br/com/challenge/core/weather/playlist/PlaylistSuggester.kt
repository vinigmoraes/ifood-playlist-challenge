package br.com.challenge.core.weather.playlist

import br.com.challenge.core.track.ports.SpotifyGateway
import br.com.challenge.core.weather.domain.Weather

class PlaylistSuggester(
    private val spotifyGateway: SpotifyGateway
) {
    fun preparePlaylist(weather: Weather) = spotifyGateway.findPlaylist(weather.playlistType.name)
}