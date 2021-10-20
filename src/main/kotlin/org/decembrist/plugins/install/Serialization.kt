package org.decembrist.plugins.install

import com.fasterxml.jackson.core.util.DefaultIndenter
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.jackson.*
import io.vertx.core.json.jackson.DatabindCodec
import io.vertx.core.json.jackson.JacksonCodec
import io.vertx.core.json.jackson.JacksonFactory
import org.decembrist.plugins.Install

object Serialization : Install({
    install(ContentNegotiation) {
        // internal vertx object mapper
        val mapper = DatabindCodec.mapper()
        mapper.setDefaultPrettyPrinter(
            DefaultPrettyPrinter().apply {
                indentArraysWith(DefaultPrettyPrinter.FixedSpaceIndenter.instance)
                indentObjectsWith(DefaultIndenter("  ", "\n"))
            }
        )

        mapper.registerKotlinModule()
        val converter = JacksonConverter(mapper)
        register(ContentType.Application.Json, converter)
    }
})