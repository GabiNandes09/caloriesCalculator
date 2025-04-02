package com.gabrielfernandes.caloriescalculator.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultTextField
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultTextValues

@Composable
fun AddFoodUI(navController: NavController) {
    Scaffold(
        containerColor = Color.Black
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            DefaultTextField(
                label = "Nome",
                modifier = Modifier.padding(20.dp)
            ) {

            }
            DefaultTextField(
                label = "Calorias em 100g",
                modifier = Modifier.padding(20.dp)
            ) {

            }
            
        }
    }
}

@Preview
@Composable
private fun Preview() {
    val navController = rememberNavController()
    AddFoodUI(navController)
}