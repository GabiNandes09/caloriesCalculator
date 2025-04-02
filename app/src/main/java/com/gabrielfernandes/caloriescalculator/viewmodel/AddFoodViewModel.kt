package com.gabrielfernandes.caloriescalculator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabrielfernandes.caloriescalculator.database.dao.FoodDAO
import com.gabrielfernandes.caloriescalculator.database.entity.Food
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AddFoodViewModel(
    private val foodDAO: FoodDAO
) : ViewModel() {
    private val name = MutableStateFlow("")
    private val kcal = MutableStateFlow(0.0)

    private val _hasError = MutableStateFlow(false)
    val hasError = _hasError.asStateFlow()

    private val _saved = MutableStateFlow(false)
    val saved = _saved.asStateFlow()

    fun setName(name: String) {
        this.name.value = name
    }

    fun setKcal(kcal: Double) {
        this.kcal.value = kcal
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