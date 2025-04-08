package com.gabrielfernandes.caloriescalculator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabrielfernandes.caloriescalculator.database.dao.FoodDAO
import com.gabrielfernandes.caloriescalculator.database.entity.Food
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ManagerPageViewModel(
    private val foodDAO: FoodDAO
) : ViewModel() {
    private val _foodList = MutableStateFlow<List<Food>>(emptyList())
    val foodList = _foodList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadFoodList()
        }
    }

    private suspend fun loadFoodList(){
        foodDAO.selectAll().collect{list ->
            _foodList.value = list
        }
    }
}