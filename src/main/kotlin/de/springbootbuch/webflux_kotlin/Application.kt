package de.springbootbuch.webflux_kotlin

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans
import org.springframework.http.MediaType.TEXT_HTML
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Mono

class Handler {
	fun sayHello(req: ServerRequest) =
			ok().body(
					Mono.just("Hello, ${req.queryParam("name").orElse("World")}"),
					String::class.java
			)

	fun andGoodbye(req: ServerRequest) =
			ok().body(
					Mono.just("Goodbye"),
					String::class.java
			)
}

class Router(val handler: Handler) {
	fun routes() = router {
		("/greetings" and accept(TEXT_HTML)).nest {
			GET("/hello", handler::sayHello)
			GET("/goodbye", handler::andGoodbye)
		}
	}
}

fun beans() = beans {
	bean<Handler>()
	bean {
		Router(ref()).routes()
	}
}

class BeansInitializer<T> :
		ApplicationContextInitializer<GenericApplicationContext> {
	override fun initialize(ctx: GenericApplicationContext)
			= beans().initialize(ctx)
}

@SpringBootApplication
class Application

fun main(args: Array<String>) {
	SpringApplication.run(Application::class.java, *args)
}