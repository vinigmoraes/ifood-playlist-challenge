package br.com.challenge.core.weather

import br.com.challenge.core.weather.domain.Chilly
import br.com.challenge.core.weather.domain.Cold
import br.com.challenge.core.weather.domain.Hot
import br.com.challenge.core.weather.domain.Warm

class WeatherFactory {

    companion object {

        private const val THIRTY_DEGREES = 30.0
        private const val FIFTEEN_DEGREES = 15.0
        private const val TEN_DEGREES = 10.0
        private const val FOURTEEN_DEGREES = 14.0

        private val warmRange = FIFTEEN_DEGREES..THIRTY_DEGREES
        private val chillyRange = TEN_DEGREES..FOURTEEN_DEGREES

        fun createWeather(temperature: Double) =
            when {
                temperature > THIRTY_DEGREES ->  Hot(temperature)
                temperature in warmRange -> Warm(temperature)
                temperature in chillyRange -> Chilly(temperature)
                else -> Cold(temperature)
            }
    }
}