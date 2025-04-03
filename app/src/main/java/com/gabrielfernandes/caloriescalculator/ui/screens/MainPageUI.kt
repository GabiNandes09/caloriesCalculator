package com.gabrielfernandes.caloriescalculator.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.gabrielfernandes.caloriescalculator.database.entity.Food
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.BackgroundUI
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultChangePositionButton
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultCleanButton
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultComboBox
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultTextField
import com.gabrielfernandes.caloriescalculator.viewmodel.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainPageUI(
    navController: NavController
) {
    Scaffold { paddingValues ->
        BackgroundUI()
        val viewModel: MainViewModel = koinViewModel()

        val foodList by viewModel.foodList.collectAsState()
        val firstItem by viewModel.firstItem.collectAsState()
        val secondItem by viewModel.secondItem.collectAsState()
        val kcalFi by viewModel.kcalFi.collectAsState()
        val gramSi by viewModel.gramSi.collectAsState()


        var requiredValue by remember { mutableStateOf("") }

        LaunchedEffect(firstItem, secondItem, requiredValue) {
            viewModel.kcalCalculator(requiredValue)
        }

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
            if (requiredValue.isNotEmpty()) {
                firstItem?.let { firstItem ->
                    Text(
                        text = "$requiredValue gramas de ${firstItem.name} tem ${
                            String.format(
                                "%.2f",
                                kcalFi
                            )
                        } calorias.",
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 30.dp)
                    )
                }
                secondItem?.let { secondItem ->
                    Text(
                        text = "Para ${secondItem.name} ter ${
                            String.format(
                                "%.2f",
                                kcalFi
                            )
                        } calorias são necessários ${String.format("%.2f", gramSi)} gramas.",
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 30.dp)
                    )
                }

            }

            DefaultChangePositionButton(
                modifier = Modifier
                    .padding(15.dp)
                    .size(55.dp),
                onChangeButtonClick = { viewModel.onChangeButtonClick() }
            )

            SecondItem(
                itens = foodList,
                onItemClick = { second -> viewModel.setSecondItem(second) },
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
        Text(
            text = "O Item ${itemSelected.name} \ntem ${itemSelected.caloriesIn100g} calorias em 100 gramas",
            color = Color.White
        )
    }

    DefaultTextField(
        modifier = Modifier.padding(horizontal = 25.dp, vertical = 10.dp),
        label = "Gramas desejadas",
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
        Text(
            text = "O Item ${itemSelected.name} \ntem ${itemSelected.caloriesIn100g} calorias em 100 gramas",
            color = Color.White
        )
    }
}

@Preview
@Composable
private fun Preview() {
    val nav = rememberNavController()
    MainPageUI(nav)
}