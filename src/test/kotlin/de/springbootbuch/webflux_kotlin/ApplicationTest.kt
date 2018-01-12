package de.springbootbuch.webflux_kotlin

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.reactive.server.WebTestClient

@RunWith(SpringRunner::class)
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