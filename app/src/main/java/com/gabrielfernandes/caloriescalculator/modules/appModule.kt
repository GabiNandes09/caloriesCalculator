package com.gabrielfernandes.caloriescalculator.modules

import com.gabrielfernandes.caloriescalculator.viewmodel.AddFoodViewModel
import com.gabrielfernandes.caloriescalculator.viewmodel.MainViewModel
import com.gabrielfernandes.caloriescalculator.viewmodel.ManagerPageViewModel
import com.gabrielfernandes.caloriescalculator.viewmodel.MealMakerChooseFoodViewModel
import org.koin.dsl.module

val appModule = module {
    single {
        MainViewModel(
            foodDAO = get()
        )
    }
    factory { (id: Int) ->
        AddFoodViewModel(
            id = id,
            foodDAO = get()
        )
    }
    factory {
        ManagerPageViewModel(
            foodDAO = get()
        )
    }
    factory {
        MealMakerChooseFoodViewModel(
            foodDAO = get()
        )
    }
}