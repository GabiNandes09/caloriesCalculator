package com.gabrielfernandes.caloriescalculator.ui.defaultComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gabrielfernandes.caloriescalculator.R

@Composable
fun BackgroundUI() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(.3f)
                    .background(colorResource(id = R.color.c1))
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(.9f)
                    .background(Color.White)
            )
        }

        Box(
            modifier = Modifier
                .size(400.dp) // Tamanho do círculo
                .align(Alignment.BottomEnd) // Alinha no canto inferior direito
                .offset(x = 250.dp, y = 150.dp) // Move para fora da tela, deixando só o primeiro quadrante visível
                .clip(CircleShape)
                .background(colorResource(id = R.color.c1))
        )
    }
}