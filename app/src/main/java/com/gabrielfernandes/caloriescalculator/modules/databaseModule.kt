package com.gabrielfernandes.caloriescalculator.modules

import androidx.room.Room
import com.gabrielfernandes.caloriescalculator.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = androidContext(),
            klass = AppDatabase::class.java,
            name = "db_KcalCalculator"
        ).build()
    }

    factory { get<AppDatabase>().FoodDAO() }

}