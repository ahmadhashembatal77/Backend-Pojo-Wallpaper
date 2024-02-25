package com.example.plugins

import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.serialization.gson.*
import io.ktor.serialization.jackson.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.application.*

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        jackson { enable(SerializationFeature.INDENT_OUTPUT) }
        gson {}
        json()
    }
}