package br.com.challenge.core.track.ports

import br.com.challenge.infrastructure.gateway.spotify.response.SpotifyPlaylistResponse

interface SpotifyGateway {

    fun playlist(keyword: String): SpotifyPlaylistResponse
}
