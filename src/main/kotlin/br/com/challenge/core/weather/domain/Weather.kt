package br.com.challenge.core.weather.domain

import br.com.challenge.core.weather.playlist.enum.PlaylistType

abstract class Weather {

    abstract val temperature: Double

    abstract fun playlistType() : String
}