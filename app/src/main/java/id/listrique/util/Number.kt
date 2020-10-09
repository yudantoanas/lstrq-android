package id.listrique.util

fun Int?.value(): Int = this ?: 0
fun Double?.value(): Double = this ?: 0.0
fun Long?.value(): Long = this ?: 0