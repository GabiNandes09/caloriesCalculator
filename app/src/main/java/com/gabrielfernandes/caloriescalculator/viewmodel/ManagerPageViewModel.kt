package com.gabrielfernandes.caloriescalculator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.sqlite.db.SimpleSQLiteQuery
import com.gabrielfernandes.caloriescalculator.database.dao.FoodDAO
import com.gabrielfernandes.caloriescalculator.database.entity.Food
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ManagerPageViewModel(
    private val foodDAO: FoodDAO
) : ViewModel() {
    private val _orderByQuery = MutableStateFlow("")

    @OptIn(ExperimentalCoroutinesApi::class)
    val foodList = _orderByQuery
        .flatMapLatest { query ->
            if (query.isBlank()) {
                foodDAO.selectAll()
            } else {
                val sqlQuery = foodDAO.buildOrderByQuery(query)
                foodDAO.selectAllWithOrderBy(sqlQuery)
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun setOrderBy(query: String) {
        _orderByQuery.value = query
    }



}