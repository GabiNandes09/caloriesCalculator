package com.gabrielfernandes.caloriescalculator.viewmodel

import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.gabrielfernandes.caloriescalculator.database.dao.FoodDAO
import com.gabrielfernandes.caloriescalculator.database.dao.MealDAO
import com.gabrielfernandes.caloriescalculator.database.entity.Food
import com.gabrielfernandes.caloriescalculator.utilities.FoodToInclude
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MealMakerViewModel(
    private val foodDAO: FoodDAO,
    private val mealDAO: MealDAO
) : ViewModel() {
    private val _includedFood = MutableStateFlow<List<FoodToInclude>>(emptyList())
    val includedFood = _includedFood.asStateFlow()

    private val _totalQTD = MutableStateFlow(0.0)
    val totalQTD = _totalQTD.asStateFlow()

    private val _totalKcal = MutableStateFlow(0.0)
    val totalKcal = _totalKcal.asStateFlow()

    fun addIncludeFood(food: FoodToInclude){
        food.calculateKcalInFood()
        _includedFood.value += food
        _totalQTD.value += food.qtd
        _totalKcal.value += food.kcalInFood
    }

    fun onSaveClick(){}

}

