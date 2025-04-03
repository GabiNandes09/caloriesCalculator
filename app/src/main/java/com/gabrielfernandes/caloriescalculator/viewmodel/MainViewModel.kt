package com.gabrielfernandes.caloriescalculator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabrielfernandes.caloriescalculator.database.dao.FoodDAO
import com.gabrielfernandes.caloriescalculator.database.entity.Food
import com.gabrielfernandes.caloriescalculator.utilities.convertToDouble
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val foodDAO: FoodDAO
) : ViewModel() {
    private val _foodList = MutableStateFlow<List<Food>>(emptyList())
    val foodList = _foodList.asStateFlow()

    private val _firstItem = MutableStateFlow<Food?>(null)
    val firstItem = _firstItem.asStateFlow()

    private val _secondItem = MutableStateFlow<Food?>(null)
    val secondItem = _secondItem.asStateFlow()

    private val _kcalFi = MutableStateFlow(0.0)
    val kcalFi = _kcalFi.asStateFlow()

    private val _gramSi = MutableStateFlow(0.0)
    val gramSi = _gramSi.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadFoodList()
        }
    }

    private suspend fun loadFoodList() {
        foodDAO.selectAll().collect { list ->
            _foodList.value = list
        }
    }

    fun setFirstItem(food: Food) {
        _firstItem.value = food
    }

    fun setSecondItem(food: Food) {
        _secondItem.value = food
    }

    fun kcalCalculator(kcal: String) {
        if (kcal.isEmpty()) return

        _firstItem.value?.let { fi ->
            val value = convertToDouble(kcal)

            _kcalFi.value = (fi.caloriesIn100g * value) / 100.0

            _secondItem.value?.let { si ->

                _gramSi.value = (100.0 * _kcalFi.value) / si.caloriesIn100g
            }
        }
    }

    fun onChangeButtonClick() {
        val newFirstItem = _secondItem.value?.copy()
        val newSecondItem = _firstItem.value?.copy()

        _secondItem.value = newSecondItem
        _firstItem.value = newFirstItem
    }

}