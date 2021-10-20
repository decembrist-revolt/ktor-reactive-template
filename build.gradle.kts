import nu.studer.gradle.jooq.JooqEdition.*
import org.jooq.meta.jaxb.Logging.WARN

val ktorVersion: String by project
val kotlinVersion: String by project
val logbackVersion: String by project
val koinVersion: String by project
val vertxVersion: String by project
val jooqVersion: String by project
val flywayVersion: String by project
val postgresqlVersion: String by project

plugins {
    application
    kotlin("jvm") version "1.5.31"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.5.31"
    id("nu.studer.jooq") version "6.0.1"
}

group = "org.decembrist"
version = "0.0.1"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

repositories {
    mavenCentral()
}

dependencies {
    // ktor
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-host-common:$ktorVersion")
    implementation("io.ktor:ktor-jackson:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    // di
    implementation("io.insert-koin:koin-ktor:$koinVersion")
    // vertx
    implementation("io.vertx:vertx-pg-client:$vertxVersion")
    implementation("io.vertx:vertx-lang-kotlin-coroutines:$vertxVersion")
    // database
    implementation("org.jooq:jooq:$jooqVersion")
    implementation("org.flywaydb:flyway-core:$flywayVersion")
    implementation("org.postgresql:postgresql:$postgresqlVersion")

    jooqGenerator("org.postgresql:postgresql:$postgresqlVersion")

    testImplementation("io.ktor:ktor-server-tests:$ktorVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")
}

jooq {
    version.set("3.15.1")  // the default (can be omitted)
    edition.set(OSS)  // the default (can be omitted)

    configurations {
        create("main") {
            generateSchemaSourceOnCompilation.set(false)
            jooqConfiguration.apply {
                logging = WARN
                jdbc.apply {
                    driver = "org.postgresql.Driver"
                    url = "jdbc:postgresql://localhost:5432/postgres"
                    user = "postgres"
                    password = "postgres"
                }
                generator.apply {
                    name = "org.jooq.codegen.KotlinGenerator"
                    database.apply {
                        inputSchema = "public"
                    }
                    generate.apply {
                        isDeprecated = false
                        isRecords = false
                        isPojos = true
                        isImmutablePojos = false
                        isFluentSetters = false
                        isDaos = false
                    }
                    target.apply {
                        packageName = "org.decembrist.generated"
                        directory = "src/generated/kotlin"
                    }
                    strategy.name = "org.jooq.codegen.DefaultGeneratorStrategy"
                }
            }
        }
    }
}