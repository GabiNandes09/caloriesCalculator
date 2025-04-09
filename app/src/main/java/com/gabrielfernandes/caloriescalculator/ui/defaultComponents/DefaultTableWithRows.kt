package com.gabrielfernandes.caloriescalculator.ui.defaultComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gabrielfernandes.caloriescalculator.database.entity.Food

@Composable
fun DefaultTableWithRows(
    modifier: Modifier = Modifier,
    rows: List<Food>,
    onRowClick: (Int) -> Unit
) {
    val headers = listOf("ID", "Nome", "kcal/100g")
    Column(
        modifier = modifier
    ) {
        Header(headers)
        LazyColumn(
            modifier = Modifier.heightIn(max = 450.dp)
        ) {
            if (rows.isEmpty()) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Nenhum item encontrado", color = Color.Gray)
                    }
                }
            }
            items(rows) { item ->
                TableItens(item, onRowClick = { onRowClick(it) })
            }
        }
    }
}

@Composable
private fun Header(
    headers: List<String>
) {
    Row(
        modifier = Modifier
            .background(Color.Gray),
        verticalAlignment = Alignment.CenterVertically
    ) {
        headers.forEach { item ->
            Text(
                text = item,
                modifier = Modifier
                    .weight(1f)
                    .border(0.5.dp, Color.Black)
                    .padding(5.dp),
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                maxLines = 1
            )
        }
    }
}

@Composable
private fun TableItens(
    food: Food,
    onRowClick: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .clickable { food.id?.let { onRowClick(it) } },
        verticalAlignment = Alignment.CenterVertically
    ) {
        TableCell(text = food.id.toString(), modifier = Modifier.weight(1f))
        TableCell(text = food.name, modifier = Modifier.weight(1f))
        TableCell(text = food.caloriesIn100g.toString(), modifier = Modifier.weight(1f))
    }
}


@Composable
fun TableCell(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .border(0.5.dp, Color.Black)
            .padding(5.dp)
            .fillMaxHeight(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            color = Color.Black
        )
    }
}


@Preview
@Composable
private fun Preview() {
    DefaultTableWithRows(rows = emptyList(), onRowClick = {})
}