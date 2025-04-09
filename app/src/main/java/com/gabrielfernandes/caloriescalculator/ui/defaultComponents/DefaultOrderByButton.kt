package com.gabrielfernandes.caloriescalculator.ui.defaultComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.gabrielfernandes.caloriescalculator.R

@Composable
fun DefaultOrderByButton(
    onOptionClick: (String) -> Unit
) {
    var expanded by remember{ mutableStateOf(false)}

    val itensList = listOf(
        OrderOption("ID 1-9", "ID ASC"),
        OrderOption("ID 9-1", "ID DESC"),
        OrderOption("Nome A-Z", "NAME ASC"),
        OrderOption("Nome Z-A", "NAME DESC"),
        OrderOption("kcal 1-9", "calories_in_100_g ASC"),
        OrderOption("kcal 9-1", "calories_in_100_g DESC"),
    )


    Column {
        IconButton(
            onClick = { expanded = true }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.filter_ico),
                contentDescription = null,
                tint = Color.Black
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            containerColor = Color.White
        ) {
            itensList.forEach { item ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = item.label,
                            color = Color.Black
                        )
                    },
                    onClick = {
                        onOptionClick(item.query)
                        expanded = false
                    }
                )
            }
        }
    }
}

private data class OrderOption(val label: String, val query: String)
