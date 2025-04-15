package com.gabrielfernandes.caloriescalculator.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import com.gabrielfernandes.caloriescalculator.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainPageViewModel() : ViewModel() {
    val itens = listOf(
        BottomItem(label = "Calculadora", icon = R.drawable.calculator_ico),
        BottomItem(label = "Refeições", icon = R.drawable.meal_maker_ico),
        BottomItem(label = "Gerenciamento", icon = R.drawable.edit_ico),
    )

    private val _selectedItem = MutableStateFlow(0)
    val selectedItem = _selectedItem.asStateFlow()

    fun chanceSelectedTo(index: Int){
        _selectedItem.value = index
    }

}

data class BottomItem(
    val label: String,
    val icon: Int
)