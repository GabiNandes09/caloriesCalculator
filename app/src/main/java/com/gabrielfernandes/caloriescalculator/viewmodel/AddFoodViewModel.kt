package com.gabrielfernandes.caloriescalculator.viewmodel

import androidx.lifecycle.ViewModel
import com.gabrielfernandes.caloriescalculator.database.dao.FoodDAO
import kotlinx.coroutines.flow.MutableStateFlow

class AddFoodViewModel(
    private val foodDAO: FoodDAO
) : ViewModel(){
    private val name = MutableStateFlow("")
    private val kcal = MutableStateFlow(0)

    fun setName(name: String){
        this.name.value = name
    }

    fun setKcal(kcal: Int){
        this.kcal.value = kcal
    }

    fun saveFood(){}
}