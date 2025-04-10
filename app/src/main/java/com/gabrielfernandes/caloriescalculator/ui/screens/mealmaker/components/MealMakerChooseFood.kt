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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gabrielfernandes.caloriescalculator.R
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultComboBox
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultTextField

@Composable
fun MealMakerChooseFood(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        DefaultComboBox(
            label = "Selecione um item",
            itens = emptyList(),
            onItemClick = {},
            selectedItem = null,
            modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 10.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            DefaultTextField(
                value = "",
                label = "Quantidade (Em gramas)",
                modifier = Modifier
                    .weight(.5f)
                    .padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
            ) {

            }
            Button(
                onClick = { /*TODO*/ },
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

@Preview
@Composable
private fun Preview() {
    MealMakerChooseFood()
}