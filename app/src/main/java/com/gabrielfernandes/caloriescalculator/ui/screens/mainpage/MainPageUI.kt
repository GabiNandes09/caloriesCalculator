package com.gabrielfernandes.caloriescalculator.ui.screens.mainpage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.gabrielfernandes.caloriescalculator.database.entity.Food
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultCleanButton
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultComboBox
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultTextField
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultTextValues
import com.gabrielfernandes.caloriescalculator.viewmodel.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainPageUI() {
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
                onItemClick = {first -> viewModel.setFirstItem(first as Food)},
                valueLabel = requiredValue,
                onValueChange = { newValue ->
                    requiredValue = newValue
                },
                itemSelected = firstItem
            )
            SecondItem(
                itens = foodList,
                onItemClick = {second -> viewModel.setSecondItem(second as Food)},
                itemSelected = secondItem
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
    itens: List<Any>,
    onItemClick: (Any) -> Unit,
    valueLabel: String,
    onValueChange: (String) -> Unit,
    itemSelected: Any?
) {
    DefaultComboBox(
        itens = itens,
        label = "Selecione um item",
        modifier = Modifier.padding(horizontal = 25.dp, vertical = 10.dp)
    ) { item ->
        onItemClick(item)
    }
    DefaultTextField(
        modifier = Modifier.padding(horizontal = 25.dp, vertical = 10.dp),
        label = valueLabel
    ) { newValue ->
        onValueChange(newValue)
    }
    if (itemSelected != null) {
        DefaultTextValues(title = "Calorias", value = "259 kcal")
        DefaultTextValues(title = "Preço", value = "R$ 5,99")
    }
}
@Composable
private fun SecondItem(
    itens: List<Any>,
    onItemClick: (Any) -> Unit,
    itemSelected: Any?
) {
    DefaultComboBox(
        itens = itens,
        label = "Selecione um item",
        modifier = Modifier.padding(horizontal = 25.dp, vertical = 10.dp)
    ) { item ->
        onItemClick(item)
    }
    if (itemSelected != null) {
        DefaultTextValues(title = "Calorias", value = "259 kcal")
        DefaultTextValues(title = "Preço", value = "R$ 5,99")
    }
}

@Preview
@Composable
private fun Preview() {
    MainPageUI()
}