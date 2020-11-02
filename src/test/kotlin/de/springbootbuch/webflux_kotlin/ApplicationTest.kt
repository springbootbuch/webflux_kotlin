package de.springbootbuch.webflux_kotlin

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient

@ExtendWith(SpringExtension::class)
class ApplicationTest {

    private val webTestClient = WebTestClient
            .bindToRouterFunction(Router(Handler()).routes())
            .build()

    @Test
    fun sayHelloWithoutParameterShouldReturnStatusOk() {
        webTestClient
                .get().uri("/greetings/hello")
                .exchange().expectStatus().isOk
                .expectBody().equals("Hello, World\n")
    }

    @Test
    fun sayHelloWithParameterShouldReturnStatusOk() {
        webTestClient
                .get().uri("/greetings/hello?name=Kotlin")
                .exchange().expectStatus().isOk
                .expectBody().equals("Hello, Kotlin\n")
    }

    @Test
    fun sayGoodbyeShouldReturnStatusOk() {
        webTestClient
                .get().uri("/greetings/goodbye")
                .exchange().expectStatus().isOk
                .expectBody().equals("Goodbye\n")
    }
}