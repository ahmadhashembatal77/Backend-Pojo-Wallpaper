val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val koin_version: String by project

plugins {
    kotlin("jvm") version "1.8.10"
    id("io.ktor.plugin") version "2.2.4"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.10"

    id("com.github.johnrengelman.shadow") version "7.1.1"
}

group = "com.example"
version = "0.0.1"
application {
    mainClass.set("com.example.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")

    kotlin {
        jvmToolchain {
            languageVersion.set(JavaLanguageVersion.of(11))
        }
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")
    // negotiation
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktor_version")
    // core
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")

    // Serializations
    implementation("io.ktor:ktor-serialization-jackson-jvm:$ktor_version")
    implementation("io.ktor:ktor-serialization-gson-jvm:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktor_version")

    // call
    implementation("io.ktor:ktor-server-call-logging-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-call-id-jvm:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")

    // auth JWT
    implementation("io.ktor:ktor-server-auth-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-auth-jwt-jvm:$ktor_version")

    // netty
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
    implementation("de.jaggl.sqlbuilder:sqlbuilder-core:2.7.2")

    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")

    // Session
//    implementation("io.ktor:ktor-server-sessions-jvm:$ktor_version")

//    Hex encodeHexString
    implementation("commons-codec:commons-codec:1.15")

    // Koin for Ktor
    implementation("io.insert-koin:koin-ktor:$koin_version")
    implementation("io.insert-koin:koin-logger-slf4j:$koin_version")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-slf4j:1.6.4")

    // Mysql Connector
//    implementation("mysql:mysql-connector-java:8.0.32")
//    implementation("org.ktorm:ktorm-support-mysql:3.6.0")

    // Ktorm Core   KTORM
    implementation("org.ktorm:ktorm-core:3.6.0")
//    implementation("me.liuwj.ktorm:ktorm-support-mysql:3.1.0")
//    implementation("me.liuwj.ktorm:ktorm-jackson:3.1.0")

    // PostGer
    implementation("org.ktorm:ktorm-support-postgresql:3.6.0")
    implementation("org.postgresql:postgresql:42.5.4")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.2")

    implementation ("io.trbl:blurhash:1.0.0")
    implementation("io.ktor:ktor-server-default-headers:2.0.1")
    implementation("io.ktor:ktor-server-metrics-micrometer:1.6.8")
    implementation("io.micrometer:micrometer-registry-prometheus:1.12.3")
}

//ktor {
//    docker {
//        jreVersion.set(io.ktor.plugin.features.JreVersion.JRE_17)
//    }
//}