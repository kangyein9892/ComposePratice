package com.example.chapter46

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chapter46.screens.Home
import com.example.chapter46.screens.Profile
import com.example.chapter46.screens.Welcome
import com.example.chapter46.ui.theme.Chapter46Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Chapter46Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.Home.route,
    ) {
        composable(NavRoutes.Home.route) {
            Home(navController = navController)
        }

        composable(NavRoutes.Welcome.route + "/{userName}") { backStackEntry ->

            val userName = backStackEntry.arguments?.getString("userName")
            Welcome(navController = navController, userName)
        }

        composable(NavRoutes.Profile.route) {
            Profile()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Chapter46Theme {
        MainScreen()
    }
}