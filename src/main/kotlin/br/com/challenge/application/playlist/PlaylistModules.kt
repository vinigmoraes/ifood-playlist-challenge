package br.com.challenge.application.playlist

import br.com.challenge.core.weather.playlist.PlaylistSuggester
import org.koin.dsl.module

val playlistModules = module {
    single { PlaylistSuggester(get()) }
}