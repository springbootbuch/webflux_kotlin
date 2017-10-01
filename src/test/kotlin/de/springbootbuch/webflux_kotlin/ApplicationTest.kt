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
                //.expectBody(String::class.java).isEqualTo<Nothing>("Hello, World\n")        //fixed with KT-5464 in Kotlin 1.2
                .expectBody().equals("Hello, World\n")
    }

    @Test
    fun sayHelloWithParameterShouldReturnStatusOk() {
        webTestClient
                .get().uri("/greetings/hello?name=Kotlin")
                .exchange().expectStatus().isOk
                //.expectBody(String::class.java).isEqualTo<Nothing>("Hello, Kotlin\n")        //fixed with KT-5464 in Kotlin 1.2
                .expectBody().equals("Hello, Kotlin\n")
    }

    @Test
    fun sayGoodbyeShouldReturnStatusOk() {
        webTestClient
                .get().uri("/greetings/goodbye")
                .exchange().expectStatus().isOk
                //.expectBody(String::class.java).isEqualTo<Nothing>("Goodbye\n")        //fixed with KT-5464 in Kotlin 1.2
                .expectBody().equals("Goodbye\n")
    }
}