package org.decembrist.plugins

import io.ktor.application.*
import io.ktor.util.*

abstract class Install(private val context: Application.() -> Unit = {}) {

    fun Application.install() = context(this)

    companion object Attributes {
        inline fun <reified T: Any> Application.newAttribute(value: T) =
            attributes.put(AttributeKey<T>(T::class.simpleName!!), value)
    }

}