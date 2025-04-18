package com.gabrielfernandes.caloriescalculator.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.Background2UI
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultAddFloatingButton
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultHeader
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultOrderByButton
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultSearchBar
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultTableWithRows
import com.gabrielfernandes.caloriescalculator.viewmodel.ManagerPageViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ManagerPageUI(navController: NavController) {
    val viewModel: ManagerPageViewModel = koinViewModel()

    val foodList by viewModel.foodList.collectAsState()
    val filter by viewModel.filter.collectAsState()

    Column {
        DefaultHeader(
            title = "Gerenciador",
            modifier = Modifier
                .fillMaxWidth()
                .weight(.1f)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(.9f)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                DefaultSearchBar(
                    value = filter,
                    onValueChange = { newValue ->
                        viewModel.setFilter(newValue)
                    }
                )
                DefaultOrderByButton { orderBy ->
                    viewModel.setOrderBy(orderBy)
                }
            }
            DefaultTableWithRows(
                modifier = Modifier.padding(horizontal = 20.dp),
                rows = foodList,
                onRowClick = { navController.navigate("addFood/${it}") }
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    val navController = rememberNavController()

    ManagerPageUI(navController)
}