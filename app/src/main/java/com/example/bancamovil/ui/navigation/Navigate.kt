package com.example.bancamovil.ui.navigation

sealed class Navigate(val route: String) {
    sealed class Screen {
        object LogiScreen : Navigate("login_screen")
        object AccountScreen: Navigate("account_screen")
        object AccountDetailScreen: Navigate("account_detail_screen")
        object FavoriteRecipe: Navigate("favorite_recipe_screen")
    }
}