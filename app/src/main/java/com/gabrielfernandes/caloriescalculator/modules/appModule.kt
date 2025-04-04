package com.gabrielfernandes.caloriescalculator.modules

import com.gabrielfernandes.caloriescalculator.viewmodel.AddFoodViewModel
import com.gabrielfernandes.caloriescalculator.viewmodel.MainViewModel
import org.koin.dsl.module

val appModule = module {
    single {
        MainViewModel(
            foodDAO = get()
        )
    }
    factory {
        AddFoodViewModel(
            foodDAO = get()
        )
    }
}