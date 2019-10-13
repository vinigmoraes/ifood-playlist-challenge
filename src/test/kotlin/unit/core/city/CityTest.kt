package unit.core.city

import br.com.challenge.core.city.City
import org.junit.Assert
import org.junit.Test

class CityTest {

    @Test
    fun `should create city successfully`() {
        val cityName = "campinas"
        val temperature = 21.0

        val city = City.create(cityName, temperature)

        Assert.assertEquals(cityName, city.name)
        Assert.assertEquals(temperature, city.temperature, 1.0)
    }
}