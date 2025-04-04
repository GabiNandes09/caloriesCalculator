package com.gabrielfernandes.caloriescalculator.ui.defaultComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gabrielfernandes.caloriescalculator.R

@Composable
fun DefaultCleanButton(
    modifier: Modifier = Modifier,
    onCleanClick: () -> Unit
) {
    Column(modifier = modifier) {
        Button(
            onClick = { onCleanClick() },
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.red))
        ) {
            Text(
                text = "LIMPAR",
                fontSize = 20.sp,
                modifier = Modifier.padding(10.dp),
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    DefaultCleanButton(
        onCleanClick = {}
    )
}