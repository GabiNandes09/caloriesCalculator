package com.gabrielfernandes.caloriescalculator.ui.defaultComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.gabrielfernandes.caloriescalculator.R

@Composable
fun DefaultDialogYesNo(
    title: String,
    message: String,
    onConfirmButtonClick: () -> Unit,
    onCancelButtonClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onCancelButtonClick() },
        confirmButton = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { onCancelButtonClick() },
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.red))
                ) {
                    Text(text = "Cancelar", color = Color.White, fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = { onConfirmButtonClick() },
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.blue))
                ) {
                    Text(text = "Confirmar")
                }
            }
        },
        containerColor = Color.LightGray,
        title = {
            Text(
                text = title,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        text = {
            Text(
                text = message,
                color = Color.Black
            )
        }
    )
}