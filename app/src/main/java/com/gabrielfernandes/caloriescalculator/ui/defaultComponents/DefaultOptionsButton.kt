package com.gabrielfernandes.caloriescalculator.ui.defaultComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
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
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DefaultOptionsButton(
    onManagerClick: () -> Unit,
    onVersionClick: () -> Unit,
    onMealMakerClick: () -> Unit
) {
    var expanded by remember {
        mutableStateOf(false)
    }


    Column {
        IconButton(onClick = { expanded = true }) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = null,
                tint = Color.Black
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            containerColor = Color.White
        ) {
            DropdownMenuItem(
                text = {
                    Text(
                        text = "Refeições",
                        color = Color.Black
                    )
                },
                onClick = {
                    expanded = false
                    onMealMakerClick()
                }
            )
            DropdownMenuItem(
                text = {
                    Text(
                        text = "Gerenciar itens",
                        color = Color.Black
                    )
                },
                onClick = {
                    expanded = false
                    onManagerClick()
                }
            )
            DropdownMenuItem(
                text = {
                    Text(
                        text = "Versão",
                        color = Color.Black
                    )
                },
                onClick = {
                    expanded = false
                    onVersionClick()
                }
            )
        }
    }
}