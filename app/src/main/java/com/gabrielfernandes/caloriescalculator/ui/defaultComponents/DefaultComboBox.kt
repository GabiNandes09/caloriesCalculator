package com.gabrielfernandes.caloriescalculator.ui.defaultComponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DefaultComboBox(
    modifier: Modifier = Modifier,
    label: String = "",
    itens: List<Any>,
    onItemClick: (Any) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var value by remember { mutableStateOf("") }

    Column(
        modifier = modifier
    ) {
        TextField(
            value = value,
            onValueChange = {},
            readOnly = true,
            label = {
                Text(
                    text = label,
                    color = Color.Black
                )
            },
            interactionSource = remember {
                MutableInteractionSource()
            }.also {
                LaunchedEffect(key1 = it) {
                    it.interactions.collectLatest { interaction ->
                        if (interaction is PressInteraction.Release) {
                            expanded = true
                        }
                    }
                }
            },
            trailingIcon = {
                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null
                )
            },
            modifier = Modifier.fillMaxWidth()
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            itens.forEach { item ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = item.toString(),
                            color = Color.Black
                        )
                    },
                    onClick = {
                        value = item.toString()
                        onItemClick(item)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    DefaultComboBox(
        label = "teste de label",
        itens = emptyList(),
        onItemClick = {}
    )
}