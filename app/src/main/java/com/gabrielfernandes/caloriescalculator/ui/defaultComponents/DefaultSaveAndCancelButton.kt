package com.gabrielfernandes.caloriescalculator.ui.defaultComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.gabrielfernandes.caloriescalculator.R

@Composable
fun DefaultSaveAndCancelButton(
    modifier: Modifier,
    onSaveClick: () -> Unit,
    onCancelClick: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { onSaveClick() },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.green)
            )
        ) {
            Text(
                text = "Salvar",
                color = Color.Black,
                fontSize = 25.sp
            )
        }
        Button(
            onClick = { onCancelClick() },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.red)
            )
        ) {
            Text(
                text = "Cancelar",
                color = Color.White,
                fontSize = 25.sp
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    DefaultSaveAndCancelButton(
        modifier = Modifier,
        onSaveClick = {},
        onCancelClick = {}
    )
}