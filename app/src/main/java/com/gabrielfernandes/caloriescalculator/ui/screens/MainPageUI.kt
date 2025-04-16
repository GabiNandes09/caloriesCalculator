package com.gabrielfernandes.caloriescalculator.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gabrielfernandes.caloriescalculator.R
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.BackgroundUI
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultAddFloatingButton
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultBottomBar
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultErrorMessage
import com.gabrielfernandes.caloriescalculator.ui.defaultComponents.DefaultOptionsButton
import com.gabrielfernandes.caloriescalculator.ui.screens.mealmaker.MealMakerUI
import com.gabrielfernandes.caloriescalculator.viewmodel.MainPageViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainPageUI(
    navController: NavController
) {
    val viewModel: MainPageViewModel = koinViewModel()
    val pagerState = rememberPagerState { viewModel.itens.size }
    val selectedItem by viewModel.selectedItem.collectAsState()

    LaunchedEffect(key1 = selectedItem) {
        pagerState.animateScrollToPage(selectedItem)
    }
    LaunchedEffect(key1 = pagerState.targetPage) {
        viewModel.chanceSelectedTo(pagerState.targetPage)
    }

    Scaffold(
        floatingActionButton = {
            DefaultAddFloatingButton {
                navController.navigate("addFood/0")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        topBar = {
            Row(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                var version by remember { mutableStateOf(false) }

                val context = LocalContext.current
                val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
                val versionName = packageInfo.versionName

                DefaultOptionsButton(
                    onVersionClick = { version = true }
                )

                if (version) {
                    DefaultErrorMessage(
                        title = "VERSÃO",
                        message = "By: Gabriel Fernandes\nVersão: $versionName"
                    ) {
                        version = false
                    }
                }
            }
        },
        bottomBar = { DefaultBottomBar(selectedItem = selectedItem) }
    ) { paddingValues ->
        BackgroundUI()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(.1f),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(RoundedCornerShape(30.dp))
                        .size(80.dp)
                )
            }
            HorizontalPager(
                state = pagerState, modifier = Modifier
                    .fillMaxWidth()
                    .weight(.7f)
            ) { page ->
                when (page) {
                    0 -> CalculatorUI(navController = navController)
                    1 -> MealMakerUI(navController = navController)
                    2 -> ManagerPageUI(navController = navController)
                }
            }
        }
    }
}