package com.gabrielfernandes.caloriescalculator.utilities

import java.text.NumberFormat
import java.util.Locale

fun convertToMoney(initial: String) : Double{
    return initial.replace(",", ".").toDouble()
}