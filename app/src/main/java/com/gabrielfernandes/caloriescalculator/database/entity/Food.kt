package com.gabrielfernandes.caloriescalculator.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    indices = [Index(value = ["name"], unique = true)]
)
data class Food(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name : String,
    @ColumnInfo(name = "calories_in_100_g")
    val caloriesIn100g: Double
){
    override fun toString(): String {
        return name;
    }
}
