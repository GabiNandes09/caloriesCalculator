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

class AddFoodViewModel(
    private val foodDAO: FoodDAO
) : ViewModel() {
    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()

    private val _kcal = MutableStateFlow(0.0)
    val kcal = _kcal.asStateFlow()

    private val _hasError = MutableStateFlow(false)
    val hasError = _hasError.asStateFlow()

    private val _saved = MutableStateFlow(false)
    val saved = _saved.asStateFlow()

    fun setName(name: String) {
        _name.value = name.replaceFirstChar { it.uppercase() }
    }

    fun setKcal(kcal: String) {
        _kcal.value = convertToDouble(kcal)
    }

    fun saveFood() {
        if (name.value.isBlank() || kcal.value <= 0) {
            _hasError.value = true
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                foodDAO.insertFood(
                    Food(
                        name = name.value,
                        caloriesIn100g = kcal.value
                    )
                )
                _saved.value = true
            } catch (e: Exception){
                e.printStackTrace()
                _hasError.value = true
            }
        }
    }

    fun resetError(){
        _hasError.value = false
    }
}