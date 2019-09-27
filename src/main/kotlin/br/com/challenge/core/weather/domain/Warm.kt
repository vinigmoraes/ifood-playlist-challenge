package br.com.challenge.core.weather.domain

import br.com.challenge.core.weather.playlist.enum.PlaylistType

class Warm(
    override val temperature: Double
) : Weather() {

    override fun playlistType(): String = PlaylistType.POP.name.toLowerCase()
}