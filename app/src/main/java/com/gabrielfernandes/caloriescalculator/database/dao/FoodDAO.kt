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
    fun selectAllWithFilterAndOrderBy(query: SupportSQLiteQuery): Flow<List<Food>>

    @Query("SELECT * FROM Food WHERE id = :id")
    fun selectById(id: Int) : Food

    @Delete
    fun delete(food: Food)

    @Update
    fun updateFood(food: Food)

    fun buildFilterAndOrderByQuery(filter: String, orderBy: String): SimpleSQLiteQuery {
        val sql = """
        SELECT * FROM Food
        WHERE 
            ID LIKE ? OR
            NAME LIKE ? OR
            calories_in_100_g LIKE ?
        ORDER BY ?, NAME ASC
    """.trimIndent()

        val likeFilter = "%${filter}%"

        return SimpleSQLiteQuery(sql, arrayOf(likeFilter, likeFilter, likeFilter, orderBy))
    }

}