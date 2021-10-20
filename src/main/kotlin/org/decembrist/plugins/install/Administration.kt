package org.decembrist.plugins.install

import io.ktor.server.engine.*
import io.ktor.application.*
import org.decembrist.plugins.Install
import org.decembrist.utils.property

object Administration : Install({
    install(ShutDownUrl.ApplicationCallFeature) {
        // A function that will be executed to get the exit code of the process
        exitCodeSupplier = { 0 } // ApplicationCall.() -> Int
    }
})
