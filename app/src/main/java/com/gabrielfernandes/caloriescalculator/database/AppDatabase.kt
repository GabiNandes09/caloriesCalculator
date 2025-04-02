package com.gabrielfernandes.caloriescalculator.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gabrielfernandes.caloriescalculator.database.dao.FoodDAO
import com.gabrielfernandes.caloriescalculator.database.entity.Food

@Database(
    entities = [Food::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

abstract fun FoodDAO() : FoodDAO
}