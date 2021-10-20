package org.decembrist.plugins.install

import io.ktor.application.*
import io.vertx.core.Vertx
import io.vertx.pgclient.PgConnectOptions
import io.vertx.pgclient.PgPool
import io.vertx.sqlclient.PoolOptions
import org.decembrist.plugins.Install
import org.decembrist.plugins.install.Database.initClient
import org.decembrist.plugins.install.Database.runMigrations
import org.decembrist.utils.injectAttribute
import org.decembrist.utils.property
import org.flywaydb.core.Flyway

object Database : Install({

    val port: Int by property("database.port")
    val host: String by property("database.host")
    val name: String by property("database.name")
    val username: String by property("database.username")
    val password: String by property("database.password")

    runMigrations(host, port, name, username, password)
    initClient(host, port, name, username, password)

}) {
    fun Application.runMigrations(host: String, port: Int, name: String, username: String, password: String) {
        val onstart: Boolean by property("flyway.onstart")

        if (onstart) {
            Flyway.configure()
                .dataSource("jdbc:postgresql://$host:$port/$name", username, password)
                .load()
                .migrate()
        }
    }

    fun Application.initClient(host: String, port: Int, name: String, username: String, password: String) {
        val pool: Int by property("database.pool")
        val vertx: Vertx by injectAttribute()

        val connectOptions = PgConnectOptions()
            .setPort(port)
            .setHost(host)
            .setDatabase(name)
            .setUser(username)
            .setPassword(password)

        val poolOptions = PoolOptions().setMaxSize(pool)

        val client = PgPool.client(vertx, connectOptions, poolOptions)

        newAttribute(client)
    }
}