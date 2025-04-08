package com.gabrielfernandes.caloriescalculator.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gabrielfernandes.caloriescalculator.database.entity.Food
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.BackgroundUI
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultHeader
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultTableWithRows

@Composable
fun ManagerPageUI() {
    val rows = listOf(
        Food(id = 1, name = "Banana", caloriesIn100g = 25.0),
        Food(id = 2, name = "Maça", caloriesIn100g = 32.0),
        Food(id = 3, name = "Pasta de amendoim penauty bunny", caloriesIn100g = 32.0)
    )
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
                    rows = rows //todo colocar a lista aqui
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    ManagerPageUI()
}