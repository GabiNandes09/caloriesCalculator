package com.gabrielfernandes.caloriescalculator.modules

import androidx.room.Room
import com.gabrielfernandes.caloriescalculator.database.AppDatabase
import com.gabrielfernandes.caloriescalculator.database.migrations.MIGRATION_1_2
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = androidContext(),
            klass = AppDatabase::class.java,
            name = "db_KcalCalculator"
        )
            .addMigrations(MIGRATION_1_2)
            .build()
    }

    factory { get<AppDatabase>().FoodDAO() }
    factory { get<AppDatabase>().MealDAO() }

}