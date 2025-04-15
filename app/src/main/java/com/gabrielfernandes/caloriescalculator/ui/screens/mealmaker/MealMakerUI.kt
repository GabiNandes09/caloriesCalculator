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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.Background2UI
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultAddFloatingButton
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultDialogYesNo
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultErrorMessage
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultGetNameDialog
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
    val nameMeal by viewModel.nameMeal.collectAsState()
    val hasError by viewModel.hasError.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    var trySave by remember { mutableStateOf(false) }
    var listEmpty by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
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
                MealMakerChooseFood { foodToAdd ->
                    try {
                        viewModel.addIncludeFood(foodToAdd)
                    } catch (e: Exception){
                        viewModel.showError(e.message ?: "Erro desconhecido")
                    }
                }
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
                    onSaveClick = {
                        if (includedFoodList.isEmpty()) {
                            listEmpty = true
                        } else {
                            trySave = true
                        }
                    },
                    onCancelClick = { navController.popBackStack() },
                    onDeleteClick = {},
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }

    if (trySave) {
        DefaultGetNameDialog(
            title = "Salvando refeição",
            label = "Insira um nome para refeição",
            value = nameMeal,
            onConfirmButtonClick = {
                viewModel.onSaveClick()
                trySave = false
            },
            onDismissRequest = { trySave = false },
            onTextChange = { newValue -> viewModel.setName(newValue) }
        )
    }

    if (listEmpty) {
        DefaultErrorMessage(
            title = "Algo deu errado",
            message = "Sua refeição necessita ao menos um ingrediente"
        ) {
            listEmpty = false
        }
    }

    if (hasError) {
        DefaultErrorMessage(
            title = "Algo está errado",
            message = errorMessage
        ) {
            viewModel.resetError()
        }
    }
}