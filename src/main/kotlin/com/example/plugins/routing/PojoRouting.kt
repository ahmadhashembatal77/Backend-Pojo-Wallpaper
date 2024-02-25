package com.example.plugins.routing

import com.example.routes.*
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.pojoRouting() {
    routing {
        /**
         * Images
         */

        route(path = "pojo_images") {
            imagesRoute()
        }

        /**
         * Categories
         */

        route(path = "pojo_category") {
            categoriesRoute()
        }

        /**
         * Collections
         */

        route(path = "pojo_collections") {
            collectionsRoute()
        }

        /**
         * Users
         */

        route("user") {
            userRoute()
        }

        /**
         * Stickers
         */

        route(path = "pojo_stickers") {
            stickersRoute()
        }
    }
}