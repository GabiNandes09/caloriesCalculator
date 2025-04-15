package com.gabrielfernandes.caloriescalculator.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.gabrielfernandes.caloriescalculator.database.entity.Ingredients
import com.gabrielfernandes.caloriescalculator.database.entity.Meal
import com.gabrielfernandes.caloriescalculator.database.entity.MealWithIngredients
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDAO {
    @Insert
    suspend fun insertMeal(meal: Meal) : Long

    @Insert
    suspend fun insertIngredients(ingredients: List<Ingredients>)

    @Transaction
    suspend fun insertMealWithIngredients(mealWithIngredients: MealWithIngredients){
        val mealId = insertMeal(mealWithIngredients.meal)
        val ingredientsWithId = mealWithIngredients.ingredients.map {
            it.copy(mealId = mealId.toInt())
        }
        insertIngredients(ingredientsWithId)
    }

    @Query("SELECT * FROM Meal")
    fun selectAll() : Flow<List<MealWithIngredients>>

    @Query("SELECT * FROM Meal WHERE id = :id")
    fun selectById(id: Int) : MealWithIngredients
}