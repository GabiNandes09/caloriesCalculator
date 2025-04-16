package com.gabrielfernandes.caloriescalculator.ui.screens.mealbook

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultHeader
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultOrderByButton
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultSearchBar
import com.gabrielfernandes.caloriescalculator.ui.screens.mealbook.components.TableMealBook
import com.gabrielfernandes.caloriescalculator.viewmodel.MealBookViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MealBookUI() {
    val viewModel: MealBookViewModel = koinViewModel()

    val mealList by viewModel.mealList.collectAsState()


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
                .weight(.9f)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                DefaultSearchBar(value = "") {

                }
                DefaultOrderByButton {

                }
            }
            TableMealBook(mealList = mealList)
        }
    }
}