package br.com.challenge.core.weather.domain

import br.com.challenge.core.weather.playlist.enum.PlaylistType

class Cold(
    override val temperature: Double
) : Weather() {

    override fun playlistType(): String = PlaylistType.CLASSICAL.name.toLowerCase()
}
