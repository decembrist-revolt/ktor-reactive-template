package org.decembrist.plugins.install

import org.decembrist.plugins.Install
import org.jooq.SQLDialect
import org.jooq.impl.DSL

object Jooq : Install({
    // disable annoying jooq logs
    System.getProperties().setProperty("org.jooq.no-logo", "true")
    System.getProperties().setProperty("org.jooq.no-tips", "true")

    val sqlBuilder = DSL.using(SQLDialect.POSTGRES)
    newAttribute(sqlBuilder)
})