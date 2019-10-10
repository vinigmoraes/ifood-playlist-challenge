package br.com.challenge.infrastructure.gateway.spotify

import br.com.challenge.core.weather.playlist.Playlist
import br.com.challenge.core.weather.playlist.ports.SpotifyGateway
import br.com.challenge.infrastructure.gateway.spotify.response.SpotifyPlaylistResponse
import com.wrapper.spotify.SpotifyApi
import com.wrapper.spotify.SpotifyHttpManager

class SpotifyGatewayAdapter(
    private val clientId: String,
    private val clientSecret: String
) : SpotifyGateway {

    private val uri = SpotifyHttpManager.makeUri("https://example.com/spotify-redirect")

    private val spotifyApi = SpotifyApi.builder()
        .setClientId(clientId)
        .setClientSecret(clientSecret)
        .setRedirectUri(uri)
        .build()
        .also {
            it.accessToken = it.clientCredentials().build().execute().accessToken
        }

    override fun playlist(keyword: String): SpotifyPlaylistResponse {

        val searchPlaylistResponse = spotifyApi.searchPlaylists(keyword).build()
            .execute().items.random()

        val tracksPlaylistResponse = spotifyApi.getPlaylistsTracks(searchPlaylistResponse.id).build()
            .execute().items.map { it.track.name }

        val playlist = Playlist(name = searchPlaylistResponse.name, tracks = tracksPlaylistResponse)

        return SpotifyPlaylistResponse(playlist)
    }
}