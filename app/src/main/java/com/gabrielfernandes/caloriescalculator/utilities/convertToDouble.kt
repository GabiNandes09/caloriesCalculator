package com.gabrielfernandes.caloriescalculator.utilities

import java.text.NumberFormat
import java.text.ParseException
import java.util.Locale

fun convertToDouble(input: String): Double {
    val format = NumberFormat.getInstance(Locale("pt", "BR"))
    return try {
        format.parse(input)?.toDouble()
            ?: throw IllegalArgumentException("Formato inválido: $input")
    } catch (e: ParseException) {
        throw IllegalArgumentException("Formato inválido: $input")
    }
}
