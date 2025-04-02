package com.gabrielfernandes.caloriescalculator.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gabrielfernandes.caloriescalculator.database.entity.Food
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDAO {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertFood(food: Food)

    @Query("SELECT * FROM Food;")
    fun selectAll() : Flow<List<Food>>

    @Query("SELECT * FROM Food WHERE id = :id")
    fun selectById(id: Int) : Food

    @Delete
    fun delete(food: Food)
}