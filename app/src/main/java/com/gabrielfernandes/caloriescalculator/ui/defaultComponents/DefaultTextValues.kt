package com.gabrielfernandes.caloriescalculator.ui.defaultComponents

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DefaultTextValues(
    modifier: Modifier = Modifier,
    title: String,
    value: String
) {
    Row(
        modifier = modifier
    ) {
        Text(
            text = "${title.uppercase()}: ",
            color = Color.White,
            modifier = Modifier.padding(end = 10.dp)
        )
        Text(
            text = value,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }

}

@Preview
@Composable
private fun Preview() {
    DefaultTextValues(
        title = "Título",
        value = "123456"
    )
}