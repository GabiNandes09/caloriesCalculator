package com.gabrielfernandes.caloriescalculator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabrielfernandes.caloriescalculator.database.dao.FoodDAO
import com.gabrielfernandes.caloriescalculator.database.entity.Food
import com.gabrielfernandes.caloriescalculator.utilities.convertToDouble
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MealMakerChooseFoodViewModel(
    private val foodDAO: FoodDAO
) : ViewModel() {
    private val _foodList = MutableStateFlow<List<Food>>(emptyList())
    val foodList = _foodList.asStateFlow()

    private val _qtd = MutableStateFlow("")
    val qtd = _qtd.asStateFlow()

    private val _selectedItem = MutableStateFlow<Food?>(null)
    val selectedItem = _selectedItem.asStateFlow()

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

    fun setQtd(qtd: String){
        _qtd.value = qtd
    }

    fun setSelectedItem(food: Food){
        _selectedItem.value = food
    }

    fun clean(){
        _selectedItem.value = null
        _qtd.value = ""
    }
}