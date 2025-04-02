package com.gabrielfernandes.caloriescalculator.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
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
import com.gabrielfernandes.caloriescalculator.database.entity.Food
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultCleanButton
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultComboBox
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultTextField
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultTextValues
import com.gabrielfernandes.caloriescalculator.viewmodel.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainPageUI(
    navController: NavController
) {
    Scaffold(
        containerColor = Color.Black
    ) { paddingValues ->
        val viewModel: MainViewModel = koinViewModel()

        val foodList by viewModel.foodList.collectAsState()
        val firstItem by viewModel.firstItem.collectAsState()
        val secondItem by viewModel.secondItem.collectAsState()


        var requiredValue by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FirstItem(
                itens = foodList,
                onItemClick = { first -> viewModel.setFirstItem(first) },
                onValueChange = { newValue ->
                    requiredValue = newValue
                },
                itemSelected = firstItem,
                onAddClick = { navController.navigate("addFood") }
            )
            if (requiredValue.isNotEmpty()){
                DefaultTextValues(title = "R$", value = requiredValue)
            }
            SecondItem(
                itens = foodList,
                onItemClick = { second -> viewModel.setSecondItem(second as Food) },
                itemSelected = secondItem,
                onAddClick = { navController.navigate("addFood") }
            )
            DefaultCleanButton(
                modifier = Modifier.padding(top = 15.dp)
            ) {

            }
        }
    }
}

@Composable
private fun FirstItem(
    itens: List<Food>,
    onItemClick: (Food) -> Unit,
    onValueChange: (String) -> Unit,
    itemSelected: Food?,
    onAddClick: () -> Unit
) {
    DefaultComboBox(
        itens = itens,
        label = "Selecione um item",
        modifier = Modifier.padding(horizontal = 25.dp, vertical = 10.dp),
        canAdd = true,
        onItemClick = { onItemClick(it as Food) },
        onAddClick = { onAddClick() }
    )
    if (itemSelected != null) {
        DefaultTextValues(title = "Calorias/100g", value = itemSelected.caloriesIn100g.toString())
        DefaultTextValues(title = "R$/g", value = "A fazer")
    }

    DefaultTextField(
        modifier = Modifier.padding(horizontal = 25.dp, vertical = 10.dp),
        label = "Calorias",
        isNumeric = true
    ) { newValue ->
        onValueChange(newValue)
    }

}

@Composable
private fun SecondItem(
    itens: List<Food>,
    onItemClick: (Food) -> Unit,
    itemSelected: Food?,
    onAddClick: () -> Unit
) {
    DefaultComboBox(
        itens = itens,
        label = "Selecione um item",
        modifier = Modifier.padding(horizontal = 25.dp, vertical = 10.dp),
        canAdd = true,
        onAddClick = { onAddClick() },
        onItemClick = { onItemClick(it as Food) }
    )

    if (itemSelected != null) {
        DefaultTextValues(title = "Calorias/100g", value = itemSelected.caloriesIn100g.toString())
        DefaultTextValues(title = "R$/g", value = "A fazer")
    }
}

@Preview
@Composable
private fun Preview() {
    val nav = rememberNavController()
    MainPageUI(nav)
}