package com.gabrielfernandes.caloriescalculator.ui.screens.mealmaker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.Background2UI
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultHeader
import com.gabrielfernandes.caloriescalculator.ui.screens.mealmaker.components.MealMakerChooseFood

@Composable
fun MealMakerUI(navController: NavController) {
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
                    .fillMaxWidth())
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(.9f)
            ) {
                MealMakerChooseFood()
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