package com.gabrielfernandes.caloriescalculator.ui.defaultComponents

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gabrielfernandes.caloriescalculator.viewmodel.MainPageViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DefaultBottomBar(
    selectedItem: Int
) {
    val viewModel: MainPageViewModel = koinViewModel()

    val items = viewModel.itens

    BottomAppBar(
        containerColor = Color.Transparent,
        actions = {
            items.forEach { item ->
                NavigationBarItem(
                    selected = selectedItem == items.indexOf(item),
                    onClick = { viewModel.chanceSelectedTo(items.indexOf(item)) },
                    icon = {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = null,
                            tint = Color.Black,
                        )
                    },
                    label = {
                        Text(
                            text = item.label,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(indicatorColor = Color.LightGray)
                )
            }
        })
}

