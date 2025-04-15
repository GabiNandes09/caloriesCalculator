package com.gabrielfernandes.caloriescalculator.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = Meal::class,
        parentColumns = ["id"],
        childColumns = ["mealId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("mealId")]
)
data class Ingredients(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val mealId: Int,
    val name: String,
    val caloriesIn100g: Double,
    val qtd: Double,
    val totalKcal: Double
)
