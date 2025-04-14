package com.gabrielfernandes.caloriescalculator.utilities

import com.gabrielfernandes.caloriescalculator.database.entity.Food

data class FoodToInclude(
    val food: Food,
    val qtd: Double,
    var kcalInFood: Double = 0.0
){
    fun calculateKcalInFood(){
        kcalInFood = (food.caloriesIn100g/100)*qtd
    }
}