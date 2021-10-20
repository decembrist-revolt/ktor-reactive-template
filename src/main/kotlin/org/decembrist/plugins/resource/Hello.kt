package org.decembrist.plugins.resource

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.decembrist.plugins.Install
import org.decembrist.service.Service
import org.koin.ktor.ext.inject

object Hello : Install({
    val service: Service by inject()

    routing {
        get("/hello") {
            val hello = service.hello()
            call.respond(hello)
        }
        get("/person") {
            val person = service.person()
            if (person == null) {
                call.respond(HttpStatusCode.NotFound)
            } else {
                call.respond(person)
            }
        }
        get("/vertx") {
            call.respond(service.vertx())
        }
    }
})
