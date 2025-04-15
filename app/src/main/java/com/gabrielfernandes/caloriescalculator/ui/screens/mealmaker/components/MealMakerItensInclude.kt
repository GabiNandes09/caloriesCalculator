package com.gabrielfernandes.caloriescalculator.ui.screens.mealmaker.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gabrielfernandes.caloriescalculator.R
import com.gabrielfernandes.caloriescalculator.database.entity.Food
import com.gabrielfernandes.caloriescalculator.utilities.FoodToInclude
import java.util.Locale

@Composable
fun MealMakerItensInclude(
    modifier: Modifier = Modifier,
    includedItens: List<FoodToInclude>,
    totalQtd: Double,
    totalKcal: Double
) {
    Card(
        modifier = modifier
            .shadow(20.dp, RoundedCornerShape(10.dp))
            .height(300.dp),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.paper)),
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Text(
            text = "ITENS NO SEU PRATO:",
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 10.dp),
            fontSize = 16.sp,
            color = Color.Black
        )
        FirstRow()
        if (includedItens.isEmpty()) {
            Text(
                text = "Comece adicionando um item",
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
                    .height(180.dp),
                textAlign = TextAlign.Center,
                color = Color.Gray,
                maxLines = 1
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            ) {
                items(includedItens) { food ->
                    ItemInMeal(food)
                }
            }
        }
        TotalRow(totalQtd, totalKcal)
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
                modifier = Modifier.width(160.dp),
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                color = Color.Black,
                fontSize = 14.sp
            )
            Text(
                text = "QTD",
                modifier = Modifier.width(80.dp),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontSize = 14.sp
            )
            Text(
                text = "KCAL",
                modifier = Modifier.width(100.dp),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End,
                color = Color.Black,
                fontSize = 14.sp
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
private fun ItemInMeal(food: FoodToInclude) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = food.food.name,
                modifier = Modifier.width(160.dp),
                maxLines = 1,
                color = Color.Black,
                fontSize = 12.sp
            )
            Text(
                text = String.format(Locale.ROOT, "%.2f", food.qtd),
                modifier = Modifier.width(80.dp),
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontSize = 12.sp
            )
            Text(
                text = String.format(Locale.ROOT, "%.2f", food.kcalInFood),
                modifier = Modifier.width(100.dp),
                textAlign = TextAlign.End,
                color = Color.Black,
                fontSize = 12.sp
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
private fun TotalRow(
    totalQtd: Double,
    totalKcal: Double
) {
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
                .padding(horizontal = 20.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "TOTAL",
                modifier = Modifier.width(160.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                maxLines = 1,
                color = Color.Black
            )
            Text(
                text = totalQtd.toString(),
                modifier = Modifier.width(80.dp),
                textAlign = TextAlign.Center,
                color = Color.Black
            )
            Text(
                text = totalKcal.toString(),
                textAlign = TextAlign.End,
                modifier = Modifier.width(100.dp),
                color = Color.Black
            )
        }
    }
}

