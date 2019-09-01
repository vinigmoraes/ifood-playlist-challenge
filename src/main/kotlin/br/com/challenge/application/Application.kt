package br.com.challenge.application

import br.com.challenge.application.health.HealthController
import br.com.challenge.application.health.health
import br.com.challenge.application.health.healthModules
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.inject

fun Application.main() {

    install(Koin){
        modules(healthModules)
    }

    val healthController  by inject<HealthController>()

    routing {
        health(healthController)
    }
}

fun main(args: Array<String>) {
    embeddedServer(Netty, 8080){
        main()
    }.start(wait = true)
}

