package br.com.challenge.application.config

import br.com.challenge.core.weather.ports.OpenWeatherGateway
import br.com.challenge.core.weather.playlist.ports.SpotifyGateway
import br.com.challenge.infrastructure.gateway.openweather.OpenWeatherGatewayAdapter
import br.com.challenge.infrastructure.gateway.spotify.SpotifyGatewayAdapter
import org.koin.dsl.module

val configModules = module {
    single {
        OpenWeatherGatewayAdapter(
            apiKey = System.getenv("OPEN_WEATHER_API_KEY"),
            url = System.getenv("OPEN_WEATHER_URL"),
            objectMapper = get()
        ) as OpenWeatherGateway
    }
    single {
        SpotifyGatewayAdapter(
            clientId = System.getenv("SPOTIFY_CLIENT_ID"),
            clientSecret = System.getenv("SPOTIFY_CLIENT_SECRET")
        ) as SpotifyGateway
    }
    single { objectMapper }
}