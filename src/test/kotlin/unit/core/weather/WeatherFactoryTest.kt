package unit.core.weather

import br.com.challenge.core.weather.WeatherFactory
import br.com.challenge.core.weather.domain.Chilly
import br.com.challenge.core.weather.domain.Cold
import br.com.challenge.core.weather.domain.Hot
import br.com.challenge.core.weather.domain.Warm
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Assert
import org.junit.Test

class WeatherFactoryTest {

    @Test
    fun `should return hot weather when temperature is higher than 30 degrees`() {
        val thirtyOneDegrees = 31.0
        val weather = WeatherFactory.createWeather(thirtyOneDegrees)

        Assert.assertThat(weather, instanceOf(Hot::class.java))
    }

    @Test
    fun `should return warm weather when temperature is between 15 and 30 degrees`() {
        val eighteenDegrees = 18.0
        val weather = WeatherFactory.createWeather(eighteenDegrees)

        Assert.assertThat(weather, instanceOf(Warm::class.java))
    }

    @Test
    fun `should return chilly weather when temperature is between 10 and 14 degrees`() {
        val fourteenDegrees = 14.0
        val weather = WeatherFactory.createWeather(fourteenDegrees)

        Assert.assertThat(weather, instanceOf(Chilly::class.java))
    }

    @Test
    fun `should return cold weather when temperature is below then 10 degrees`() {
        val eightDegrees = 8.0
        val weather = WeatherFactory.createWeather(eightDegrees)

        Assert.assertThat(weather, instanceOf(Cold::class.java))
    }
}