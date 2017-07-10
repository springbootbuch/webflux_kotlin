package de.springbootbuch.helloworld

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType.TEXT_HTML
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Mono

@SpringBootApplication
class Application {
    @Component
    class Handler {
        fun sayHello(req: ServerRequest) =
            ok().body(
                    Mono.just("Hello, ${req.queryParam("name").orElse("World")}"),
                    String::class.java
            )

        fun goodbye(req: ServerRequest) =
            ok().body(
                    Mono.just("Goodbye"),
                    String::class.java
            )
    }

    @Component
    class RoutesConfig(val handler: Handler) {
        @Bean
        fun routes() = router {
            ("/greetings" and accept(TEXT_HTML)).nest {
                GET("/hello", handler::sayHello)
                GET("/goodbye", handler::goodbye)
            }
        }
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}