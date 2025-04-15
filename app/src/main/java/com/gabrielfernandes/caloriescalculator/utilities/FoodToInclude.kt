package com.gabrielfernandes.caloriescalculator.utilities

import com.gabrielfernandes.caloriescalculator.database.entity.Food
import com.gabrielfernandes.caloriescalculator.database.entity.Ingredients

data class FoodToInclude(
    val food: Food,
    val qtd: Double,
    var kcalInFood: Double = 0.0
){
    fun calculateKcalInFood(){
        kcalInFood = (food.caloriesIn100g/100)*qtd
    }
    fun convertToIngredient() : Ingredients{
        return Ingredients(
            mealId = 0,
            name = food.name,
            caloriesIn100g = food.caloriesIn100g,
            qtd = qtd,
            totalKcal = kcalInFood
        )
    }
}