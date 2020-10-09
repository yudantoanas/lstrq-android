package id.listrique.util

import java.text.NumberFormat
import java.util.*

const val ZERO_RUPIAH = "Rp 0"

fun Int?.toCurrencyString(): String {

    val localeId = Locale("in", "ID")

    val currencyFormatter = NumberFormat.getCurrencyInstance(localeId).apply {
        maximumFractionDigits = 0
        isParseIntegerOnly = true
    }

    val formatted = this?.let { currencyFormatter.format(it) } ?: currencyFormatter.format(0)

    val replace = String.format(
        "[Rp\\s]",
        NumberFormat.getCurrencyInstance().currency.getSymbol(localeId)
    )

    return formatted.replace(replace, "").replace("Rp", "Rp ")
}

fun Long?.toCurrencyString(): String {

    val localeId = Locale("in", "ID")

    val currencyFormatter = NumberFormat.getCurrencyInstance(localeId).apply {
        maximumFractionDigits = 0
        isParseIntegerOnly = true
    }

    val formatted = this?.let { currencyFormatter.format(it) } ?: currencyFormatter.format(0)

    val replace = String.format(
        "[Rp\\s]",
        NumberFormat.getCurrencyInstance().currency.getSymbol(localeId)
    )

    return formatted.replace(replace, "").replace("Rp", "Rp ")
}

fun Float?.toCurrencyString(): String {

    val localeId = Locale("in", "ID")

    val currencyFormatter = NumberFormat.getCurrencyInstance(localeId).apply {
        maximumFractionDigits = 0
        isParseIntegerOnly = true
    }

    val formatted = this?.let { currencyFormatter.format(it) } ?: currencyFormatter.format(0)

    val replace = String.format(
        "[Rp\\s]",
        NumberFormat.getCurrencyInstance().currency.getSymbol(localeId)
    )

    return formatted.replace(replace, "").replace("Rp", "Rp ")
}

fun Double?.toCurrencyString(): String {

    val localeId = Locale("in", "ID")

    val currencyFormatter = NumberFormat.getCurrencyInstance(localeId).apply {
        maximumFractionDigits = 0
        isParseIntegerOnly = true
    }

    val formatted = this?.let { currencyFormatter.format(it) } ?: currencyFormatter.format(0)

    val replace = String.format(
        "[Rp\\s]",
        NumberFormat.getCurrencyInstance().currency.getSymbol(localeId)
    )

    return formatted.replace(replace, "").replace("Rp", "Rp ")
}

fun String?.isZeroRupiah(): Boolean = this == ZERO_RUPIAH

fun String?.toCurrencyString(): String = this?.longValue().toCurrencyString()

fun String?.floatToCurrencyString(): String = this?.floatValue().toCurrencyString()

fun String?.longValue(): Long = this?.toLong()?.let { it } ?: 0L

fun String?.intValue(): Int = this?.toInt()?.let { it } ?: 0

fun String?.floatValue(): Float = this?.toFloat()?.let { it } ?: 0F

fun String?.fromCurrencyToLong(): Long =
    if (this != null && this.isNotEmpty())
        replace("Rp ", "").replace(".", "").toLong()
    else 0