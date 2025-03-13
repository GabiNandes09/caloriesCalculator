package com.gabrielfernandes.caloriescalculator.ui.screens.mainpage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultComboBox

@Composable
fun MainPageUI() {
    Scaffold(
        containerColor = Color.Black
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues).fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DefaultComboBox(
                itens = emptyList(),
                modifier = Modifier.padding(horizontal = 25.dp, vertical = 10.dp)
            ) {
                
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    MainPageUI()
}