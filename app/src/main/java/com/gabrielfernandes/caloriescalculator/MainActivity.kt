package com.gabrielfernandes.caloriescalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gabrielfernandes.caloriescalculator.ui.screens.AddFoodUI
import com.gabrielfernandes.caloriescalculator.ui.screens.MainPageUI
import com.gabrielfernandes.caloriescalculator.ui.screens.ManagerPageUI
import com.gabrielfernandes.caloriescalculator.ui.screens.mealmaker.MealMakerUI
import com.gabrielfernandes.caloriescalculator.ui.theme.CaloriesCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CaloriesCalculatorTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "main") {
                    composable("main") {
                        MainPageUI(navController = navController)
                    }
                    composable(
                        route = "addFood/{id}",
                        arguments = listOf(navArgument("id") {
                            type = NavType.IntType
                        })
                    ) { entry ->
                        entry.arguments?.getInt("id")?.let { id ->
                            AddFoodUI(navController, id)
                        }
                    }
                    composable("manager") {
                        ManagerPageUI(navController)
                    }
                    composable("mealMaker"){
                        MealMakerUI()
                    }
                }

            }
        }
    }
}