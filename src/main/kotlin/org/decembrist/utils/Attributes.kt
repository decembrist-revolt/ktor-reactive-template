package org.decembrist.utils

import io.ktor.application.*
import io.ktor.util.*

inline fun <reified T : Any> Application.injectAttribute(): Lazy<T> = lazy {
    val value = attributes.allKeys
        .firstOrNull { it.name == T::class.simpleName!! }
        ?.let { attributes[it as AttributeKey<T>] }
    if (null !is T && value == null) {
        error("attribute of type ${T::class} not found")
    } else value as T
}