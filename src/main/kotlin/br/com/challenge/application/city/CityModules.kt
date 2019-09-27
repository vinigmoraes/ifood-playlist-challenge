package br.com.challenge.application.city

import br.com.challenge.core.city.CityService
import org.koin.dsl.module

val cityModules = module {
    single { CityController(get()) }
    single { CityService(get(), get()) }
}