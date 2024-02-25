package com.example.domain.usecases.util


/*
make page size in range from 10 to 20
 */
fun Int.makePageSizeInRange(): Int {
    return this.coerceIn(0..150)
}

fun Int.makePageNumberDefaultIfItZero(): Int {
    return if (this == 0) 1 else this
}

fun Int.pageNumberToCheckIfPageExist(totalPages: Int): Boolean {
    return if (this > totalPages) false
    else totalPages >= this
}

fun Int.pageNumberToMakeItInRange(totalPages: Int): Int {
    return if (this > totalPages) totalPages else this
}

fun String.isValidEmail(): Boolean {
    val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    return emailRegex.matches(this)
}