package com.gabrielfernandes.caloriescalculator.ui.screens.mealmaker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.Background2UI
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

    Scaffold { paddingValues ->
        Background2UI(
            color1Weight = .15f
        )
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            DefaultHeader(
                title = "Monte seu prato",
                modifier = Modifier
                    .weight(.15f)
                    .fillMaxWidth()
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(.9f)
            ) {
                MealMakerChooseFood { foodToAdd -> viewModel.addIncludeFood(foodToAdd) }
                MealMakerItensInclude(
                    modifier = Modifier.padding(20.dp),
                    includedItens = includedFoodList,
                    totalQtd = totalQtd,
                    totalKcal = totalKcal
                )
                DefaultSaveAndCancelButton(
                    isEditing = false,
                    onSaveClick = { /*TODO*/ },
                    onCancelClick = { navController.popBackStack() },
                    onDeleteClick = {},
                    modifier = Modifier.fillMaxWidth()
                )
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