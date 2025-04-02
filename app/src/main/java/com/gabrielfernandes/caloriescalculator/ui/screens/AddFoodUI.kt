package com.gabrielfernandes.caloriescalculator.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultErrorMessage
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultSaveAndCancelButton
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultTextField
import com.gabrielfernandes.caloriescalculator.viewmodel.AddFoodViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddFoodUI(navController: NavController) {
    val viewModel: AddFoodViewModel = koinViewModel()

    val hasError by viewModel.hasError.collectAsState()
    val savedItem by viewModel.saved.collectAsState()

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
            ) { name ->
                viewModel.setName(name)
            }
            DefaultTextField(
                label = "Calorias em 100g",
                modifier = Modifier.padding(20.dp),
                isNumeric = true
            ) { kcal ->
                if (kcal.isNotEmpty()){
                    viewModel.setKcal(kcal)
                }
            }

            DefaultSaveAndCancelButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                onSaveClick = { viewModel.saveFood() },
                onCancelClick = { navController.popBackStack() }
            )
        }

    }

    if (hasError) {
        DefaultErrorMessage(
            title = "Algo deu errado",
            message = "Cheque os campos. \n As calorias devem ser escritas apenas com os números."
        ) {
            viewModel.resetError()
        }
    }

    LaunchedEffect(savedItem) {
        if (savedItem){
            navController.popBackStack()
        }
    }
}

@Preview
@Composable
private fun Preview() {
    val navController = rememberNavController()
    AddFoodUI(navController)
}