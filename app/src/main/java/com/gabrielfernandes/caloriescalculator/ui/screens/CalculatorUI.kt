package com.gabrielfernandes.caloriescalculator.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gabrielfernandes.caloriescalculator.database.entity.Food
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultChangePositionButton
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultCleanButton
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultComboBox
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultHeader
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultTextField
import com.gabrielfernandes.caloriescalculator.viewmodel.MainViewModel
import org.koin.androidx.compose.koinViewModel
import java.util.Locale

@Composable
fun CalculatorUI(
    modifier: Modifier = Modifier,
    navController: NavController
) {

    val viewModel: MainViewModel = koinViewModel()

    val foodList by viewModel.foodList.collectAsState()
    val firstItem by viewModel.firstItem.collectAsState()
    val secondItem by viewModel.secondItem.collectAsState()
    val kcalFi by viewModel.kcalFi.collectAsState()
    val gramSi by viewModel.gramSi.collectAsState()
    val requiredValue by viewModel.requiredValue.collectAsState()

    LaunchedEffect(firstItem, secondItem, requiredValue) {
        viewModel.kcalCalculator(requiredValue)
    }

    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(.1f),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            DefaultHeader(
                title = "Calculadora de calorias",
                modifier = Modifier.padding(top = 10.dp)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .weight(.9f),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FirstItem(
                itens = foodList,
                onItemClick = { first -> viewModel.setFirstItem(first) },
                itemSelected = firstItem,
                onAddClick = { navController.navigate("addFood/0") }
            )
            SecondItem(
                itens = foodList,
                onItemClick = { second -> viewModel.setSecondItem(second) },
                itemSelected = secondItem,
                onAddClick = { navController.navigate("addFood/0") }
            )
            DefaultTextField(
                modifier = Modifier.padding(horizontal = 25.dp, vertical = 10.dp),
                label = "Gramas desejados",
                value = requiredValue,
                isNumeric = true
            ) { newValue ->
                viewModel.setRequiredValue(newValue)
            }

            if (requiredValue.isNotEmpty()) {
                firstItem?.let { firstItem ->
                    Text(
                        text = "$requiredValue gramas de ${firstItem.name} tem ${
                            String.format(
                                Locale.ROOT,
                                "%.2f",
                                kcalFi
                            )
                        } calorias.",
                        color = Color.Black,
                        modifier = Modifier.padding(horizontal = 30.dp)
                    )
                }
                secondItem?.let { secondItem ->
                    Text(
                        text = "Para ${secondItem.name} ter ${
                            String.format(
                                Locale.ROOT,
                                "%.2f",
                                kcalFi
                            )
                        } calorias são necessários ${
                            String.format(
                                Locale.ROOT, "%.2f", gramSi
                            )
                        } gramas.",
                        color = Color.Black,
                        modifier = Modifier.padding(horizontal = 30.dp)
                    )
                }

            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                DefaultCleanButton(
                    modifier = Modifier.padding(top = 15.dp)
                ) {
                    viewModel.onCleanButtonClick()
                }
                DefaultChangePositionButton(
                    modifier = Modifier
                        .padding(15.dp)
                        .size(55.dp),
                    onChangeButtonClick = { viewModel.onChangeButtonClick() }
                )
            }
        }
    }
}



@Composable
private fun FirstItem(
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
        selectedItem = itemSelected,
        onItemClick = { onItemClick(it as Food) },
        onAddClick = { onAddClick() }
    )
    if (itemSelected != null) {
        Text(
            text = "O Item ${itemSelected.name} \ntem ${itemSelected.caloriesIn100g} calorias em 100 gramas",
            color = Color.Black
        )
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
        selectedItem = itemSelected,
        onAddClick = { onAddClick() },
        onItemClick = { onItemClick(it as Food) }
    )

    if (itemSelected != null) {
        Text(
            text = "O Item ${itemSelected.name} \ntem ${itemSelected.caloriesIn100g} calorias em 100 gramas",
            color = Color.Black
        )
    }
}