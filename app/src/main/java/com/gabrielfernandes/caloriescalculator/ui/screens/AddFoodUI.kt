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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.BackgroundUI
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultErrorMessage
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultHeader
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultSaveAndCancelButton
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultTextField
import com.gabrielfernandes.caloriescalculator.viewmodel.AddFoodViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddFoodUI(navController: NavController) {
    val viewModel: AddFoodViewModel = koinViewModel()

    val hasError by viewModel.hasError.collectAsState()
    val savedItem by viewModel.saved.collectAsState()
    val name by viewModel.name.collectAsState()
    val kcal by viewModel.kcal.collectAsState()

    Scaffold { paddingValues ->
        BackgroundUI()
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            DefaultHeader(
                title = "Cadastro de itens",
                subTitle = "Preencha todos os campos",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp)
                    .weight(.2f)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
                    .weight(.8f),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DefaultTextField(
                    label = "Nome",
                    value = name,
                    modifier = Modifier.padding(bottom = 20.dp)
                ) { newName ->
                    viewModel.setName(newName)
                }

                DefaultTextField(
                    label = "Calorias em 100g",
                    isNumeric = true,
                    value = kcal.toString(),
                    modifier = Modifier.padding(bottom = 20.dp)
                ) { newKcal ->
                    if (newKcal.isNotEmpty()) {
                        viewModel.setKcal(newKcal)
                    }
                }

                DefaultSaveAndCancelButton(
                    modifier = Modifier.fillMaxWidth(),
                    onSaveClick = { viewModel.saveFood() },
                    onCancelClick = { navController.popBackStack() }
                )
            }
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
        if (savedItem) {
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