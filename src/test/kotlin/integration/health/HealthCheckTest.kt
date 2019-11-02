package integration.health

import br.com.challenge.application.main
import io.ktor.application.Application
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication
import org.junit.Assert
import org.junit.Test
import utils.readJsonResponse

class HealthCheckTest {

    @Test
    fun `should return http status code 200 and application health status`() =
        withTestApplication(Application::main) {
            val expectedResponse = readJsonResponse("health_check_successful")

            val response = handleRequest(HttpMethod.Get, "/health") {
                addHeader("Content-Type", "application/json")
            }.response

            Assert.assertEquals(HttpStatusCode.OK, response.status())
            Assert.assertEquals(expectedResponse, response.content)
        }
}