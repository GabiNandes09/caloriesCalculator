package com.gabrielfernandes.caloriescalculator.utilities

fun convertToDouble(initial: String) : Double{
    return initial.replace(",", ".").toDouble()
}