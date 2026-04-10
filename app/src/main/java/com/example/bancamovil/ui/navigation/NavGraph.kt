package com.example.bancamovil.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bancamovil.ui.screen.account.AccountScreen
import com.example.bancamovil.ui.screen.detailAccount.DetailAccountScreen
import com.example.bancamovil.ui.screen.login.LoginScreen

@ExperimentalAnimationApi
@Composable
fun SetupNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Navigate.Screen.LogiScreen.route) {
            LoginScreen(navController = navController)
        }
        val accountScreen = Navigate.Screen.AccountScreen.route
        composable("$accountScreen/{userId}", arguments =listOf(
                navArgument("userId") { type = NavType.IntType }))
        { navBackStackEntry ->
            val userId = navBackStackEntry.arguments?.getInt("userId") ?: 0
            AccountScreen(navController = navController, userId)
        }

        val detailAccountScreen = Navigate.Screen.AccountDetailScreen.route
        composable("$detailAccountScreen/{userId}/{cardId}", arguments =listOf(
            navArgument("userId") { type = NavType.IntType },
            navArgument("cardId") { type = NavType.IntType }
            )
        ) { navBackStackEntry ->
            val userId = navBackStackEntry.arguments?.getInt("userId") ?: 0
            val cardId = navBackStackEntry.arguments?.getInt("cardId") ?: 0
            DetailAccountScreen(navController = navController, userId = userId, cardId = cardId)
        }

    }
}