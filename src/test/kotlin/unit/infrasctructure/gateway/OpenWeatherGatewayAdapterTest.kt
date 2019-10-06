package unit.infrasctructure.gateway

import br.com.challenge.application.config.objectMapper
import br.com.challenge.infrastructure.gateway.openweather.OpenWeatherGatewayAdapter
import com.github.kittinunf.fuel.core.FuelManager
import io.mockk.every
import org.junit.Assert
import org.junit.Test

class OpenWeatherGatewayAdapterTest {

    private val apiKey = "b77e07f479efe92156376a8b07640ced"
    private val url = "http://api.openweathermap.org/data/2.5/weather"

    private val client = FuelManager.instance

    private val openWeatherGatewayAdapter = OpenWeatherGatewayAdapter(apiKey, url, objectMapper)

    @Test
    fun `should return temperature successfully`() {
        val cityName = "campinas"
        val expectedTemperature = 21.0

        val temperature = openWeatherGatewayAdapter.getCityTemperature(cityName)

        Assert.assertEquals(expectedTemperature, temperature, 1.0)
    }

    private fun url(cityName: String) = "$url?q=$cityName&units=metric&appid=$apiKey"

}