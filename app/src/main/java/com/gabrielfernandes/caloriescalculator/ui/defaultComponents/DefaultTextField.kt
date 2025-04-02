package com.gabrielfernandes.caloriescalculator.ui.defaultComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DefaultTextField(
    modifier: Modifier = Modifier,
    label: String = "",
    onValueChange: (String) -> Unit
) {
    var value by remember { mutableStateOf("") }

    Column(modifier = modifier) {
        TextField(
            value = value,
            onValueChange = { newValue ->
                value = newValue
                onValueChange(newValue)
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = label)
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Gray,
                unfocusedContainerColor = Color.Gray,
                unfocusedTextColor = Color.Black,
                focusedTextColor = Color.Black,
                unfocusedLabelColor = Color.Black,
                focusedLabelColor = Color.Black
            )
        )
    }
}

@Preview
@Composable
private fun Preview() {
    DefaultTextField(
        label = "Teste de label",
        onValueChange = {}
    )
}