package com.gabrielfernandes.caloriescalculator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabrielfernandes.caloriescalculator.database.dao.MealDAO
import com.gabrielfernandes.caloriescalculator.database.entity.MealWithIngredients
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MealBookViewModel(
    private val mealDAO: MealDAO
) : ViewModel() {
    private val _filter = MutableStateFlow("")
    val filter = _filter.asStateFlow()

    private val _orderQuery = MutableStateFlow("")

    @OptIn(ExperimentalCoroutinesApi::class)
    val mealList = combine(_filter, _orderQuery){ filter, orderBy ->
        Pair(filter, orderBy)
    }.flatMapLatest { (filter, orderBy) ->
        val query = mealDAO.buildFilterAndOrderByQuery(filter, orderBy)
        mealDAO.selectAllMealWithIngredientsWithFilterAndOderBy(query)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun setFilter(filter: String) {
        _filter.value = filter
    }

    fun setOrderBy(orderBy: String){
        _orderQuery.value = orderBy
    }


}