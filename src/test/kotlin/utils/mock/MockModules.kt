package utils.mock

import br.com.challenge.application.config.objectMapper
import br.com.challenge.core.weather.playlist.ports.SpotifyGateway
import br.com.challenge.core.weather.ports.OpenWeatherGateway
import br.com.challenge.infrastructure.gateway.openweather.OpenWeatherGatewayAdapter
import br.com.challenge.infrastructure.gateway.spotify.SpotifyGatewayAdapter
import org.koin.dsl.module

val mockModules = module {
    single {
        OpenWeatherGatewayAdapter(
            apiKey = "b77e07f479efe92156376a8b07640ced",
            url = "http://localhost:1090/weather",
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