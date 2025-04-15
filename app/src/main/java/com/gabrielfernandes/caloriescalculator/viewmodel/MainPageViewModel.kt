package com.gabrielfernandes.caloriescalculator.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainPageViewModel() : ViewModel() {
    val itens = listOf(
        BottomItem(label = "Calculadora", icon = Icons.Default.Person),
        BottomItem(label = "Refeições", icon = Icons.AutoMirrored.Filled.List),
        BottomItem(label = "Gerenciamento", icon = Icons.Default.Search),
    )

    private val _selectedItem = MutableStateFlow(0)
    val selectedItem = _selectedItem.asStateFlow()

    fun chanceSelectedTo(index: Int){
        _selectedItem.value = index
    }

}

data class BottomItem(
    val label: String,
    val icon: ImageVector
)