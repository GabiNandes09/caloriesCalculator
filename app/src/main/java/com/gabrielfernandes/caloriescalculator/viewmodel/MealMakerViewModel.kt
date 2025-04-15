package com.gabrielfernandes.caloriescalculator.viewmodel

import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabrielfernandes.caloriescalculator.database.dao.FoodDAO
import com.gabrielfernandes.caloriescalculator.database.dao.MealDAO
import com.gabrielfernandes.caloriescalculator.database.entity.Food
import com.gabrielfernandes.caloriescalculator.database.entity.Meal
import com.gabrielfernandes.caloriescalculator.database.entity.MealWithIngredients
import com.gabrielfernandes.caloriescalculator.utilities.FoodToInclude
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

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

    private val _mealList = MutableStateFlow<List<MealWithIngredients>>(emptyList())
    val mealList = _mealList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadMeal()
        }
    }

    fun addIncludeFood(food: FoodToInclude) {
        food.calculateKcalInFood()
        _includedFood.value += food
        _totalQTD.value += food.qtd
        _totalKcal.value += food.kcalInFood
    }

    fun onSaveClick() {
        viewModelScope.launch(Dispatchers.IO) {
            mealDAO.insertMealWithIngredients(MealWithIngredients(
                meal = Meal(
                    name = "Teste2",
                    totalGrams = _totalQTD.value,
                    totalKcal = _totalKcal.value
                ),
                ingredients = _includedFood.value.map {
                    it.convertToIngredient()
                }
            ))
        }
    }

    private suspend fun loadMeal(){
        mealDAO.selectAll().collect{list ->
            _mealList.value = list
        }
    }

}

