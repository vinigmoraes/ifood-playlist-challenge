package br.com.challenge.application.health

import org.koin.dsl.module

val healthModules = module {
    single { HealthController() }
}