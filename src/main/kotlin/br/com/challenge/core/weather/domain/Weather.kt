package br.com.challenge.core.weather.domain

abstract class Weather {

    abstract val temperature: Double

    abstract fun playlistType() : String
}