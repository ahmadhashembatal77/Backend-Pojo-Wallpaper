package com.example.utils

object Constants {

    /**
     * Mysql
     */
//    const val DATABASE_URL = "jdbc:mysql://db4free.net:3306/pojoservicedb"
//    const val DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver"
//    const val DATABASE_USER = "pojoservicedb"
//    const val DATABASE_PASSWORD = "ahmadbbatal3d2d3l5y"

    /**
     * PostGer
     */
    const val DATABASE_URL =
        "jdbc:postgresql://dpg-cmsde1icn0vc73bgfpm0-a.frankfurt-postgres.render.com/pojo_database_name_9bol"
    const val DATABASE_DRIVER = "org.postgresql.Driver"
//    const val DATABASE_USER = "pojo_user_database"
//    const val DATABASE_PASSWORD = "kguITyf41pkYBKeKo7ZIRfdTV2vOKKka"

    /**
     * Root
     */
//    const val DATABASE_URL_ROOT = "jdbc:mysql://localhost:3306/pojo_test"
//    const val DATABASE_USER_ROOT = "root"
//    const val DATABASE_PASSWORD_ROOT = "ghgh"

    const val SALTED_HASH_ALGORITHM = "SHA1PRNG"

    /**
     * Jwt
     */
//    const val ROOT_ISSUER = "http://127.0.0.1:8080"
//    const val ROOT_AUDIENCE = "user_root"

    val SECRET = System.getenv("JWT_SECRET") ?: "jwtsecret"
    const val ISSUER = "http://db4free.net"
    const val AUDIENCE = "http://db4free.net/pojoservicedb"
    const val REFRESH_TOKEN_EXPIRE_DATE = (365L * 1000L * 60L * 60L * 24L)

    /**
     * Other Issues
     */
//    const val FIFTEEN_LIMIT_IMAGE = 15
}