package org.decembrist.utils

import io.ktor.application.*
import kotlin.reflect.full.isSubclassOf

inline fun <reified T> Application.property(property: String): Lazy<T> = lazy {
    val value = environment.config.propertyOrNull(property)
    if (null !is T && value == null) error("property $property is null")
    when {
        String::class.isSubclassOf(T::class) -> value!!.getString() as T
        Int::class.isSubclassOf(T::class) -> value!!.getString().toInt() as T
        Long::class.isSubclassOf(T::class) -> value!!.getString().toLong() as T
        Boolean::class.isSubclassOf(T::class) -> value!!.getString().toBoolean() as T
        else -> throw UnsupportedOperationException("property type ${T::class} not supported")
    }
}