package unit.infrastructure.gateway.openweather

import br.com.challenge.application.config.objectMapper
import br.com.challenge.infrastructure.gateway.openweather.OpenWeatherGatewayAdapter
import org.junit.Assert
import org.junit.BeforeClass
import org.junit.Test
import utils.MockServer

class OpenWeatherGatewayAdapterTest {

    private val apiKey = "b77e07f479efe92156376a8b07640ced"
    private val url = "http://localhost:1090/weather"
    private val openWeatherGatewayAdapter = OpenWeatherGatewayAdapter(apiKey, url, objectMapper)
    private val cityName = "Campinas"
    private val latitude = 50f
    private val longitude = 30f

    companion object {
        @BeforeClass
        @JvmStatic
        fun setUp() {
            MockServer.start()
        }
    }

    @Test
    fun `should return temperature successfully given city name`() {
        val response = openWeatherGatewayAdapter.getCityTemperature(cityName)

        Assert.assertEquals(cityName, response!!.name)
        Assert.assertEquals(10.53, response.main.temp, 1.0)
    }

    @Test
    fun `should not return temperature when city was not find by name`() {
        val cityName = "test"

        val response = openWeatherGatewayAdapter.getCityTemperature(cityName)

        Assert.assertNull(response)
    }

    @Test
    fun `should return temperature successfully given city coordinates`() {
        val response = openWeatherGatewayAdapter.getCityTemperature(latitude, longitude)

        Assert.assertEquals(cityName, response!!.name)
        Assert.assertEquals(10.53, response.main.temp, 1.0)
    }
}