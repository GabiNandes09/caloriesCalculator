package com.gabrielfernandes.caloriescalculator.ui.screens.mealbook

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultHeader
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultOrderByButton
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultSearchBar
import com.gabrielfernandes.caloriescalculator.ui.screens.mealbook.components.TableMealBook
import com.gabrielfernandes.caloriescalculator.viewmodel.MealBookViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MealBookUI(
    onBeginClick: () -> Unit
) {
    val viewModel: MealBookViewModel = koinViewModel()

    val mealList by viewModel.mealList.collectAsState()
    val filter by viewModel.filter.collectAsState()


    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        DefaultHeader(
            title = "Livro de receitas",
            modifier = Modifier
                .fillMaxWidth()
                .weight(.1f)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .weight(.75f)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                DefaultSearchBar(value = filter) { newValue ->
                    viewModel.setFilter(newValue)
                }
                DefaultOrderByButton { orderBy ->
                    viewModel.setOrderBy(orderBy)
                }
            }
            if (mealList.isNotEmpty()) {
                TableMealBook(mealList = mealList)
            } else {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Comece criando uma nova refeição", color = Color.Gray)
                    Button(onClick = { onBeginClick() }) {
                        Text(text = "Começar")
                    }
                }
            }
        }
    }
}