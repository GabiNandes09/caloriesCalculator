package com.gabrielfernandes.caloriescalculator.ui.defaultComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun DefaultHeader(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String = ""
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            fontSize = 30.sp,
            maxLines = 1,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = subTitle,
            fontSize = 20.sp,
            maxLines = 1,
            color = Color.Black
        )
    }
}

@Preview
@Composable
private fun Preview() {
    DefaultHeader(title = "Titulo fica aqui", subTitle = "Algo a mais")
}