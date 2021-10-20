package org.decembrist.plugins.resource

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*
import org.decembrist.plugins.Install

object ResourceExceptions: Install({
    install(StatusPages) {
        exception<Exception> { ex ->
            ex.printStackTrace()
            call.respond(HttpStatusCode.InternalServerError)
        }
        exception<UserException> { ex ->
            ex.printStackTrace()
            call.respond(HttpStatusCode.BadRequest, ex.message ?: "Unknown")
        }
    }
}) {
    open class UserException(message: String): RuntimeException(message)
}