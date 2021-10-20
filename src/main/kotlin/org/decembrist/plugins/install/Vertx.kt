package org.decembrist.plugins.install

import io.vertx.core.Vertx.vertx
import org.decembrist.plugins.Install

object Vertx : Install({
    newAttribute(vertx())
})