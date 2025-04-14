package com.gabrielfernandes.caloriescalculator.ui.screens.mealmaker.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gabrielfernandes.caloriescalculator.R
import com.gabrielfernandes.caloriescalculator.database.entity.Food
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultComboBox
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultTextField
import com.gabrielfernandes.caloriescalculator.viewmodel.MealMakerChooseFoodViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MealMakerChooseFood(
    modifier: Modifier = Modifier,
    onAddClick: (Food) -> Unit
) {

    val viewModel: MealMakerChooseFoodViewModel = koinViewModel()

    val qtd by viewModel.qtd.collectAsState()
    val foodList by viewModel.foodList.collectAsState()
    val selectedItem by viewModel.selectedItem.collectAsState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        DefaultComboBox(
            label = "Selecione um item",
            itens = foodList,
            onItemClick = { viewModel.setSelectedItem(it as Food) },
            selectedItem = selectedItem,
            modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 10.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            DefaultTextField(
                value = qtd,
                isNumeric = true,
                label = "Quantidade (Em gramas)",
                modifier = Modifier
                    .weight(.5f)
                    .padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
            ) { newValue ->
                viewModel.setQtd(newValue)
            }
            Button(
                onClick = { selectedItem?.let { onAddClick(it) } },
                modifier = Modifier
                    .weight(.3f)
                    .padding(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.red))
            ) {
                Text(text = "ADICIONAR", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}