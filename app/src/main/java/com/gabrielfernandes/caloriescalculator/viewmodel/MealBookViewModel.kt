package com.gabrielfernandes.caloriescalculator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabrielfernandes.caloriescalculator.database.dao.MealDAO
import com.gabrielfernandes.caloriescalculator.database.entity.MealWithIngredients
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MealBookViewModel(
    private val mealDAO: MealDAO
) : ViewModel() {
    private val _mealList = MutableStateFlow<List<MealWithIngredients>>(emptyList())
    val mealList = _mealList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadMealList()
        }
    }

    private suspend fun loadMealList(){
        mealDAO.selectAll().collect{list ->
            _mealList.value = list
        }
    }
}