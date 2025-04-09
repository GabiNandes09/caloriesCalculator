package com.gabrielfernandes.caloriescalculator.ui.defaultComponents

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DefaultComboBox(
    modifier: Modifier = Modifier,
    label: String = "",
    itens: List<Any>,
    onItemClick: (Any) -> Unit,
    selectedItem: Any?,
    canAdd: Boolean = false,
    onAddClick: () -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }
    var value by remember { mutableStateOf("") }

    LaunchedEffect(selectedItem) {
        value = selectedItem?.toString() ?: ""
    }

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
            modifier = Modifier.fillMaxWidth().border(2.dp,Color.Black, RectangleShape),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                unfocusedTextColor = Color.Black,
                focusedTextColor = Color.Black,
                unfocusedLabelColor = Color.Black,
                focusedLabelColor = Color.Black
            )
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.widthIn(min = 300.dp, max = 350.dp).heightIn(max = 300.dp).border(1.dp, Color.Black),
            containerColor = Color.White
        ) {
            if (canAdd){
                DropdownMenuItem(
                    modifier = Modifier.border(.5.dp, Color.Black),
                    text = {
                        Text(
                            text = "Adicionar novo",
                            color = Color.Black
                        )
                    },
                    onClick = {
                        expanded = false
                        onAddClick()
                    }
                )
            }
            itens.forEach { item ->
                DropdownMenuItem(
                    modifier = Modifier.border(.5.dp, Color.Black),
                    text = {
                        Text(
                            text = item.toString(),
                            color = Color.Black
                        )
                    },
                    onClick = {
                        expanded = false
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
        selectedItem = null,
        onItemClick = {}
    )
}