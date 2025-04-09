package com.gabrielfernandes.caloriescalculator.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.BackgroundUI
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultDialogYesNo
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultErrorMessage
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultHeader
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultOptionsButton
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultSaveAndCancelButton
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultTextField
import com.gabrielfernandes.caloriescalculator.viewmodel.AddFoodViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun AddFoodUI(navController: NavController, id: Int) {
    val viewModel: AddFoodViewModel = koinViewModel(parameters = { parametersOf(id) })

    val hasError by viewModel.hasError.collectAsState()
    val savedItem by viewModel.saved.collectAsState()
    val name by viewModel.name.collectAsState()
    val kcal by viewModel.kcal.collectAsState()
    val isEditing by viewModel.isEditing.collectAsState()

    var tryDelete by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .fillMaxWidth()
            ) {
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .padding(start = 20.dp, top = 10.dp)
                        .size(30.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
    ) { paddingValues ->
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
                    .weight(.15f)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
                    .weight(.8f),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(75.dp))
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
                    value = kcal,
                    modifier = Modifier.padding(bottom = 20.dp)
                ) { newKcal ->
                    viewModel.setKcal(newKcal)
                }

                DefaultSaveAndCancelButton(
                    modifier = Modifier.fillMaxWidth(),
                    isEditing = isEditing,
                    onSaveClick = { viewModel.saveFood() },
                    onCancelClick = { navController.popBackStack() },
                    onDeleteClick = {

                    }
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

    if (true){
        DefaultDialogYesNo(
            title = "Deletar item?",
            message = "Essa ação é IRREVERSÍVEL, deseja continuar?",
            onCancelButtonClick = {tryDelete = false},
            onConfirmButtonClick = {
                viewModel.onDeleteClick()
                navController.popBackStack()
            }
        )
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
    AddFoodUI(navController, 0)
}