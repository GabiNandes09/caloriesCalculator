package com.gabrielfernandes.caloriescalculator.database.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        // Criar a tabela Meal
        db.execSQL("""
            CREATE TABLE IF NOT EXISTS Meal (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                totalGrams REAL NOT NULL,
                totalKcal REAL NOT NULL
            )
        """.trimIndent())

        // Índice único no name (como definido no @Entity)
        db.execSQL("""
            CREATE UNIQUE INDEX IF NOT EXISTS index_Meal_name ON Meal(name)
        """.trimIndent())

        // Criar a tabela Ingredients
        db.execSQL("""
            CREATE TABLE IF NOT EXISTS Ingredients (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                mealId INTEGER NOT NULL,
                name TEXT NOT NULL,
                caloriesIn100g REAL NOT NULL,
                qtd REAL NOT NULL,
                totalKcal REAL NOT NULL,
                FOREIGN KEY(mealId) REFERENCES Meal(id) ON DELETE CASCADE
            )
        """.trimIndent())

        // Criar índice na coluna mealId
        db.execSQL("CREATE INDEX IF NOT EXISTS index_Ingredients_mealId ON Ingredients(mealId)")
    }
}

