package com.gabrielfernandes.caloriescalculator.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gabrielfernandes.caloriescalculator.database.dao.FoodDAO
import com.gabrielfernandes.caloriescalculator.database.dao.MealDAO
import com.gabrielfernandes.caloriescalculator.database.entity.Food
import com.gabrielfernandes.caloriescalculator.database.entity.Ingredients
import com.gabrielfernandes.caloriescalculator.database.entity.Meal
import com.gabrielfernandes.caloriescalculator.database.entity.MealWithIngredients

@Database(
    entities = [
        Food::class,
        Meal::class,
        Ingredients::class
    ],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun FoodDAO(): FoodDAO
    abstract fun MealDAO(): MealDAO
}