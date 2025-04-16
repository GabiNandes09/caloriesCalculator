package com.gabrielfernandes.caloriescalculator.ui.screens.mealbook.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gabrielfernandes.caloriescalculator.database.entity.Ingredients
import com.gabrielfernandes.caloriescalculator.database.entity.Meal
import com.gabrielfernandes.caloriescalculator.database.entity.MealWithIngredients

@Composable
fun TableMealBook(
    modifier: Modifier = Modifier,
    mealList: List<MealWithIngredients>
) {

    LazyColumn(modifier) {
        items(mealList) { mealWithIngredients ->
            ItemMealBook(mealWithIngredients = mealWithIngredients)
        }
    }
}

@Composable
fun ItemMealBook(
    modifier: Modifier = Modifier,
    mealWithIngredients: MealWithIngredients
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = mealWithIngredients.meal.id.toString(),
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = mealWithIngredients.meal.name,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(text = "${mealWithIngredients.meal.totalGrams} g", color = Color.Black)
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = "${mealWithIngredients.meal.totalKcal} kcal", color = Color.Black)
                }
            }
            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp
                    else Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = Color.Black
                )
            }
        }
        if (expanded) {
            LazyColumn(
                modifier = Modifier.heightIn(max = 120.dp)
            ) {
                items(mealWithIngredients.ingredients) { ingredient ->
                    ItemIngredientsMealBook(ingredients = ingredient)
                }
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(.5.dp)
                .background(Color.Black)
        )
    }
}

@Composable
fun ItemIngredientsMealBook(ingredients: Ingredients) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp)
    ) {
        Column(
            modifier = Modifier
                .border(.25.dp, Color.Black)
                .fillMaxWidth()
                .padding(start = 10.dp, top = 5.dp, bottom = 5.dp)
        ) {
            Text(
                text = ingredients.name,
                color = Color.Black
            )
            Row {
                Text(
                    text = "${ingredients.qtd} g",
                    modifier = Modifier.width(80.dp),
                    maxLines = 1,
                    color = Color.Black
                )
                Text(
                    text = "${ingredients.totalKcal} kcal",
                    modifier = Modifier.width(80.dp),
                    maxLines = 1,
                    color = Color.Black
                )
            }
        }
    }
}