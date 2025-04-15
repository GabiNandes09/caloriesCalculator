package com.gabrielfernandes.caloriescalculator.ui.defaultComponents

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gabrielfernandes.caloriescalculator.R

@Composable
fun DefaultGetNameDialog(
    title: String,
    label: String,
    value: String,
    onTextChange: (String) -> Unit,
    onDismissRequest: () -> Unit,
    onConfirmButtonClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDismissRequest() },
        confirmButton = {
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { onConfirmButtonClick() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(
                            id = R.color.green
                        )
                    ),
                    modifier = Modifier.width(130.dp)
                ) {
                    Text(
                        text = "Salvar",
                        color = Color.Black,
                        fontSize = 20.sp
                    )
                }
                Button(
                    onClick = { onConfirmButtonClick() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(
                            id = R.color.red
                        )
                    ),
                    modifier = Modifier.width(130.dp)
                ) {
                    Text(
                        text = "Cancelar",
                        color = Color.White,
                        fontSize = 20.sp
                    )
                }
            }
        },
        containerColor = Color.LightGray,
        modifier = Modifier.border(2.dp, Color.Black, RoundedCornerShape(25.dp)),
        title = {
            Text(
                text = title,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )
        },
        text = {
            DefaultTextField(
                value = value,
                label = label
            ) { newValue -> onTextChange(newValue) }
        }
    )
}