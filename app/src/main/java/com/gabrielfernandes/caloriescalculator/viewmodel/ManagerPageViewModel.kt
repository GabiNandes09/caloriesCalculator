package com.gabrielfernandes.caloriescalculator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabrielfernandes.caloriescalculator.database.dao.FoodDAO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn

class ManagerPageViewModel(
    private val foodDAO: FoodDAO
) : ViewModel() {
    private val _orderByQuery = MutableStateFlow("")

    private val _filter = MutableStateFlow("")
    val filter = _filter.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val foodList = combine(_filter, _orderByQuery) { filter, orderBy ->
        Pair(filter, orderBy)
    }.flatMapLatest { (filter, orderBy) ->
        if (filter.isBlank() && orderBy.isBlank()) {
            foodDAO.selectAll()
        } else {
            val query = foodDAO.buildFilterAndOrderByQuery(filter, orderBy)
            foodDAO.selectAllWithFilterAndOrderBy(query)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun setOrderBy(query: String) {
        _orderByQuery.value = query
    }

    fun setFilter(filter: String){
        _filter.value = filter
    }

}