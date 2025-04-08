package com.gabrielfernandes.caloriescalculator.ui.screens

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
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.BackgroundUI
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultHeader
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultTableWithRows
import com.gabrielfernandes.caloriescalculator.viewmodel.ManagerPageViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ManagerPageUI(navController: NavController) {
    val viewModel: ManagerPageViewModel = koinViewModel()

    val foodList by viewModel.foodList.collectAsState()

    Scaffold { paddingValues ->
        BackgroundUI()
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            DefaultHeader(
                title = "Gerenciador", modifier = Modifier
                    .fillMaxWidth()
                    .weight(.2f),
                subTitle = "Clique para editar"
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(.7f)
            ) {
                DefaultTableWithRows(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    rows = foodList
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    val navController = rememberNavController()

    ManagerPageUI(navController)
}