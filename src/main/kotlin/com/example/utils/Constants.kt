package com.example.utils

object Constants {
    /**
     * PostGer
     */
    const val DATABASE_URL =""
    const val DATABASE_DRIVER = "org.postgresql.Driver"
    const val SALTED_HASH_ALGORITHM = "SHA1PRNG"
    val SECRET = System.getenv("JWT_SECRET") ?: "jwtsecret"
    const val ISSUER = "http://db4free.net"
    const val AUDIENCE = "http://db4free.net/pojoservicedb"
    const val REFRESH_TOKEN_EXPIRE_DATE = (365L * 1000L * 60L * 60L * 24L)
}
