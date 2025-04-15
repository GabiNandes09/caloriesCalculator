package com.gabrielfernandes.caloriescalculator.ui.defaultComponents

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun DefaultBottomBar(
    selectedItem: Int
) {

    val items = listOf(
        BottomItem(label = "Home", icon = Icons.Default.Home)
    )

    BottomAppBar(
        containerColor = Color.Transparent,
        actions = {
            items.forEach { item ->
                NavigationBarItem(
                    selected = selectedItem == items.indexOf(item),
                    onClick = { },
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = null,
                            tint = Color.Black
                        )
                    },
                    label = { Text(text = item.label, color = Color.Black) }
                )
            }
        })
}

data class BottomItem(
    val label: String,
    val icon: ImageVector
)