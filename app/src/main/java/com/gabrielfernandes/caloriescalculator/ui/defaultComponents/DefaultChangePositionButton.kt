package com.gabrielfernandes.caloriescalculator.ui.defaultComponents

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gabrielfernandes.caloriescalculator.R

@Composable
fun DefaultChangePositionButton(
    modifier: Modifier = Modifier,
    onChangeButtonClick: () -> Unit
) {
    IconButton(
        onClick = { onChangeButtonClick() },
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = R.drawable.change_button_ico),
            contentDescription = null,
            tint = Color.Blue,
            modifier = Modifier.clip(CircleShape).fillMaxSize()
        )
    }
}