package com.gabrielfernandes.caloriescalculator.ui.screens.mealmaker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.Background2UI
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultAddFloatingButton
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultHeader
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultSaveAndCancelButton
import com.gabrielfernandes.caloriescalculator.ui.screens.mealmaker.components.MealMakerChooseFood
import com.gabrielfernandes.caloriescalculator.ui.screens.mealmaker.components.MealMakerItensInclude
import com.gabrielfernandes.caloriescalculator.viewmodel.MealMakerViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MealMakerUI(navController: NavController) {
    val viewModel: MealMakerViewModel = koinViewModel()

    val includedFoodList by viewModel.includedFood.collectAsState()
    val totalQtd by viewModel.totalQTD.collectAsState()
    val totalKcal by viewModel.totalKcal.collectAsState()
    val mealList by viewModel.mealList.collectAsState()

    Scaffold(
        floatingActionButton = {
            DefaultAddFloatingButton {
                navController.navigate("addFood/0")
            }
        }
    ) { paddingValues ->
        Background2UI(
            color1Weight = .15f
        )
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            DefaultHeader(
                title = "Monte sua refeição",
                modifier = Modifier
                    .weight(.15f)
                    .fillMaxWidth()
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(800.dp)
                    .weight(.9f)
            ) {
                item {
                    MealMakerChooseFood { foodToAdd -> viewModel.addIncludeFood(foodToAdd) }
                }
                item {
                    MealMakerItensInclude(
                        modifier = Modifier.padding(20.dp),
                        includedItens = includedFoodList,
                        totalQtd = totalQtd,
                        totalKcal = totalKcal
                    )
                }
                item {
                    DefaultSaveAndCancelButton(
                        isEditing = false,
                        onSaveClick = { viewModel.onSaveClick() },
                        onCancelClick = { navController.popBackStack() },
                        onDeleteClick = {},
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    val navController = rememberNavController()

    MealMakerUI(navController)
}