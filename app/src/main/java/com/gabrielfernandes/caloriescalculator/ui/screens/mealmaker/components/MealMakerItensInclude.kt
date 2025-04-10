package com.gabrielfernandes.caloriescalculator.ui.screens.mealmaker.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gabrielfernandes.caloriescalculator.database.entity.Food

@Composable
fun MealMakerItensInclude(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
    ) {
        val itensInMeal = listOf(
            Food(name = "Banana", caloriesIn100g = 25.0),
            Food(name = "Banana", caloriesIn100g = 25.0),
            Food(name = "Banana", caloriesIn100g = 25.0),
            Food(name = "Banana", caloriesIn100g = 25.0)
        )
        Text(
            text = "ITENS NO SEU PRATO:",
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            fontSize = 20.sp
        )
        FirstRow()
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(itensInMeal) { food ->
                ItemInMeal(food)
            }
        }
        TotalRow()
    }
}

@Composable
private fun FirstRow() {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "NOME",
                modifier = Modifier.width(180.dp),
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )
            Text(
                text = "QTD",
                modifier = Modifier.width(80.dp),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Text(
                text = "KCAL",
                modifier = Modifier.width(80.dp),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End
            )
        }
        Spacer(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.Black)
        )
    }
}

@Composable
private fun ItemInMeal(food: Food) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = food.name,
                modifier = Modifier.width(180.dp),
                maxLines = 1
            )
            Text(
                text = "50",
                modifier = Modifier.width(80.dp),
                textAlign = TextAlign.Center
            )
            Text(
                text = "${food.caloriesIn100g}",
                modifier = Modifier.width(80.dp),
                textAlign = TextAlign.End
            )
        }
        Spacer(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.Black)
        )
    }
}

@Composable
private fun TotalRow() {
    Column {
        Spacer(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.Black)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "TOTAL",
                modifier = Modifier.width(180.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                maxLines = 1
            )
            Text(
                text = "500",
                modifier = Modifier.width(80.dp),
                textAlign = TextAlign.Center
            )
            Text(
                text = "1380.0",
                modifier = Modifier.width(80.dp),
                textAlign = TextAlign.End
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    MealMakerItensInclude()
}

