package org.decembrist.utils

import io.vertx.sqlclient.Row

inline fun <reified T> Row.mapTo(): T = toJson().mapTo(T::class.java)