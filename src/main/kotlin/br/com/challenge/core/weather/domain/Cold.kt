package br.com.challenge.core.weather.domain

import br.com.challenge.core.weather.playlist.enum.PlaylistType

class Cold(
    override val temperature: Double,
    override val playlistType: PlaylistType = PlaylistType.CLASSICAL
) : Weather()
