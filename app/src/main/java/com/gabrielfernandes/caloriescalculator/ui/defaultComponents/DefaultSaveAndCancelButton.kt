package com.gabrielfernandes.caloriescalculator.ui.defaultComponents

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gabrielfernandes.caloriescalculator.R

@Composable
fun DefaultSaveAndCancelButton(
    saveText: String = "SALVAR",
    cancelText: String = "CANCELAR",
    modifier: Modifier = Modifier,
    isEditing: Boolean,
    onSaveClick: () -> Unit,
    onCancelClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { onSaveClick() },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.green)
            ),
            border = BorderStroke(1.dp, Color.Black),
            modifier = Modifier.shadow(2.dp, RoundedCornerShape(25.dp))
        ) {
            Text(
                text = if (isEditing) "Atualizar" else saveText,
                color = Color.Black,
                fontSize = 25.sp
            )
        }
        Button(
            onClick = { onCancelClick() },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.red)
            ),
            border = BorderStroke(1.dp, Color.Black),
            modifier = Modifier.shadow(2.dp, RoundedCornerShape(25.dp))
        ) {
            Text(
                text = cancelText,
                color = Color.White,
                fontSize = 25.sp
            )
        }
        if (isEditing) {
            IconButton(onClick = { onDeleteClick() }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    DefaultSaveAndCancelButton(
        modifier = Modifier,
        isEditing = false,
        onSaveClick = {},
        onCancelClick = {},
        onDeleteClick = {}
    )
}