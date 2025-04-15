package com.gabrielfernandes.caloriescalculator.ui.defaultComponents

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.gabrielfernandes.caloriescalculator.R

@Composable
fun DefaultAddFloatingButton(
    onClick: () -> Unit
) {
    IconButton(
        onClick = { onClick() },
        colors = IconButtonDefaults.iconButtonColors(containerColor = colorResource(id = R.color.c1)),
        modifier = Modifier.size(60.dp).border(1.dp, Color.Black, CircleShape).shadow(10.dp, CircleShape)
        ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = null,
            modifier = Modifier.size(40.dp),
            tint = Color.DarkGray
        )
    }
}