package br.com.challenge.core.weather.domain

import br.com.challenge.core.weather.playlist.enum.PlaylistType

class Warm(
    override val temperature: Double,
    override val playlistType: PlaylistType = PlaylistType.POP
) : Weather()