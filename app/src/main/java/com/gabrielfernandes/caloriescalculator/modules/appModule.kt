package com.gabrielfernandes.caloriescalculator.modules

import com.gabrielfernandes.caloriescalculator.viewmodel.AddFoodViewModel
import com.gabrielfernandes.caloriescalculator.viewmodel.CalculatorViewModel
import com.gabrielfernandes.caloriescalculator.viewmodel.MainPageViewModel
import com.gabrielfernandes.caloriescalculator.viewmodel.ManagerPageViewModel
import com.gabrielfernandes.caloriescalculator.viewmodel.MealBookViewModel
import com.gabrielfernandes.caloriescalculator.viewmodel.MealMakerChooseFoodViewModel
import com.gabrielfernandes.caloriescalculator.viewmodel.MealMakerViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        MainPageViewModel()
    }
    single {
        CalculatorViewModel(
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
    factory {
        MealMakerViewModel(
            foodDAO = get(),
            mealDAO = get()
        )
    }
    viewModel {
        MealBookViewModel(
            mealDAO = get()
        )
    }
}