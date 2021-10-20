KTOR reactive template

Template uses:
* **ktor**
* **koin**
* **vertx**
* **jooq**
* **flyway**

Template configured to use pure sql (or jooq builder) with **reactive** postgres driver (with [vertx](https://vertx.io/docs/vertx-pg-client/java/))  
Database [config](src/main/kotlin/org/decembrist/plugins/install/Database.kt)  
Query [example](src/main/kotlin/org/decembrist/service/Service.kt) person() method  

Migrations with flyway included: _resources/db/migration_ folder

Template configured to use di with [Koin](src/main/kotlin/org/decembrist/plugins/install/Koin.kt)  
Service [example](src/main/kotlin/org/decembrist/service/Service.kt)  

There is also included vertx to use (e.g. eventbus)  
Vertx eventbus use [example](src/main/kotlin/org/decembrist/service/Service.kt) vertx() method  

 _+_ [Utils functions](src/main/kotlin/org/decembrist/utils)
 
To start:  
`gradlew run`  
To enable auto-reload/recompile on changes also run:  
`gradlew -t build`

To run jooq codegen use _generateJooq_ task and jooq configuration in _build.gradle.kts_  
_src/generated_ folder contains database classes