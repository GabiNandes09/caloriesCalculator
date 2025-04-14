package com.gabrielfernandes.caloriescalculator.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class MealWithIngredients(
    @Embedded
    val meal: Meal,
    @Relation(
        parentColumn = "id",
        entityColumn = "mealId"
    )
    val ingredients: List<Ingredients>
)
