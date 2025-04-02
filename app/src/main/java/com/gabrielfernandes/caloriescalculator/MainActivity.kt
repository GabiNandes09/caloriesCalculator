package com.gabrielfernandes.caloriescalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gabrielfernandes.caloriescalculator.ui.screens.mainpage.MainPageUI
import com.gabrielfernandes.caloriescalculator.ui.theme.CaloriesCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CaloriesCalculatorTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "main" ){
                    composable("main"){
                        MainPageUI(navController = navController)
                    }
                    composable("addFood"){

                    }
                }

            }
        }
    }
}