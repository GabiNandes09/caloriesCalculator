package com.gabrielfernandes.caloriescalculator.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Update
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.gabrielfernandes.caloriescalculator.database.entity.Food
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDAO {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertFood(food: Food)

    @Query("SELECT * FROM Food ORDER BY Food.name")
    fun selectAll() : Flow<List<Food>>

    @RawQuery(observedEntities = [Food::class])
    fun selectAllWithOrderBy(query: SupportSQLiteQuery): Flow<List<Food>>

    @Query("SELECT * FROM Food WHERE id = :id")
    fun selectById(id: Int) : Food

    @Delete
    fun delete(food: Food)

    @Update
    fun updateFood(food: Food)

    fun buildOrderByQuery(orderBy: String): SimpleSQLiteQuery {
        val sql = "SELECT * FROM Food ORDER BY ${orderBy}, NAME ASC"
        return SimpleSQLiteQuery(sql)
    }
}