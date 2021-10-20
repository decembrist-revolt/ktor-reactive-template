package org.decembrist.plugins.install

import io.ktor.application.*
import io.vertx.core.Vertx
import io.vertx.sqlclient.SqlClient
import org.decembrist.plugins.Install
import org.decembrist.service.Service
import org.decembrist.utils.injectAttribute
import org.jooq.DSLContext
import org.koin.ktor.ext.Koin.Feature
import org.koin.dsl.module

object Koin : Install({
    val dbClient: SqlClient by injectAttribute()
    val sqlBuilder: DSLContext by injectAttribute()
    val vertx: Vertx by injectAttribute()

    install(Feature) {
        modules(module(moduleDeclaration = {
            single { dbClient }
            single { sqlBuilder }
            single { vertx }
            single { Service(get(), get(), get()) }
        }))
    }
})