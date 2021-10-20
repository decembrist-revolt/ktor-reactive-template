package org.decembrist.plugins.install

import io.ktor.http.content.*
import io.ktor.routing.*
import org.decembrist.plugins.Install

object Static: Install({
    routing {
        // Static plugin. Try to access `/static/index.html`
        static("/static") {
            resources("static")
        }
    }
})