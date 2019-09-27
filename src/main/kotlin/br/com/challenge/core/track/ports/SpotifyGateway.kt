package br.com.challenge.core.track.ports

import br.com.challenge.core.weather.playlist.Playlist

interface SpotifyGateway {

    fun findPlaylist(categoryId: String): List<Playlist>
}
