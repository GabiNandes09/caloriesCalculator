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

    private val _nameMeal = MutableStateFlow("")
    val nameMeal = _nameMeal.asStateFlow()

    private val _hasError = MutableStateFlow(false)
    val hasError = _hasError.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    fun addIncludeFood(food: FoodToInclude) {
        food.calculateKcalInFood()
        _includedFood.value += food
        _totalQTD.value += food.qtd
        _totalKcal.value += food.kcalInFood
    }

    fun onSaveClick() {
        try {
            if (_nameMeal.value.isNotEmpty()){
                viewModelScope.launch(Dispatchers.IO) {
                    mealDAO.insertMealWithIngredients(MealWithIngredients(
                        meal = Meal(
                            name = _nameMeal.value,
                            totalGrams = _totalQTD.value,
                            totalKcal = _totalKcal.value
                        ),
                        ingredients = _includedFood.value.map {
                            it.convertToIngredient()
                        }
                    ))
                }
            } else {
                showError("Insira um nome para sua refeição")
            }
        } catch (e: Exception){
            _hasError.value = true
            e.printStackTrace()
        }
    }

    fun setName(name: String){
        _nameMeal.value = name
    }

    fun resetError(){
        _hasError.value = false
    }

    fun showError(message: String){
        _hasError.value = true
        _errorMessage.value = message
    }

    fun onCleanClick(){
        _includedFood.value = emptyList()
    }

}

