package com.gabrielfernandes.caloriescalculator.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Transaction
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
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

    @RawQuery(observedEntities = [MealWithIngredients::class])
    fun selectAllMealWithIngredientsWithFilterAndOderBy(query: SupportSQLiteQuery) : Flow<List<MealWithIngredients>>

    fun buildFilterAndOrderByQuery(filter: String, orderBy: String): SimpleSQLiteQuery {
        val baseSql = StringBuilder(
            """
        SELECT * FROM Meal
        WHERE 
            ID LIKE ? OR
            NAME LIKE ?
        """.trimIndent()
        )

        if (orderBy.isNotBlank() && !orderBy.contains("calories_in_100_g")) {
            baseSql.append(" ORDER BY $orderBy")
        } else if (orderBy.equals("calories_in_100_g ASC")){
            baseSql.append(" ORDER BY totalKcal ASC")
        } else if (orderBy.equals("calories_in_100_g DESC")){
            baseSql.append(" ORDER BY totalKcal DESC")
        }

        val likeFilter = "%$filter%"
        return SimpleSQLiteQuery(baseSql.toString(), arrayOf(likeFilter, likeFilter))
    }
}