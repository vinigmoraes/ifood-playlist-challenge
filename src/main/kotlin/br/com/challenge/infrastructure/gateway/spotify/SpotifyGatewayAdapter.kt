package br.com.challenge.infrastructure.gateway.spotify

import br.com.challenge.core.track.ports.SpotifyGateway
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

    override fun findPlaylist(categoryId: String) {

        val playlist = spotifyApi.getCategorysPlaylists(categoryId).build().execute()

    }
}