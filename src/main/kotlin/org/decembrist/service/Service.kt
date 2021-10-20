package org.decembrist.service

import io.vertx.core.Vertx
import io.vertx.kotlin.coroutines.await
import io.vertx.sqlclient.SqlClient
import org.decembrist.dto.HelloDto
import org.decembrist.generated.tables.pojos.Person
import org.decembrist.generated.tables.references.PERSON
import org.decembrist.utils.mapTo
import org.jooq.DSLContext

class Service(
    private val dbClient: SqlClient,
    private val sqlBuilder: DSLContext,
    private val vertx: Vertx,
) {
    fun hello() = HelloDto("hello")

    suspend fun person(): Person? = dbClient.query(sqlBuilder.selectFrom(PERSON).sql)
        .execute().await()
        .firstOrNull()
        ?.mapTo()

    suspend fun vertx(): String {
        vertx.eventBus().consumer<String>("address") {
            it.reply(it.body() + " OK")
        }
        val response = vertx.eventBus().request<String>("address", "Eventbus message").await()
        return response.body()
    }
}