package com.gabrielfernandes.caloriescalculator.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.gabrielfernandes.caloriescalculator.utilities.FoodToInclude

@Entity(
    indices = [Index(value = ["name"], unique = true)]
)
data class Meal(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    val totalGrams: Double,
    val totalKcal: Double
)
