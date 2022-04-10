package com.developersancho.rortycompose.presentation.main

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.developersancho.jetframework.SetupSystemUi
import com.developersancho.rortycompose.app.theme.JetRortyColors
import com.developersancho.rortycompose.app.theme.JetRortyTheme
import com.developersancho.rortycompose.presentation.NavGraphs
import com.developersancho.rortycompose.provider.AppNavigationProvider
import com.developersancho.rortycompose.provider.shouldUseDarkMode
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency

@Composable
fun MainRoot(viewModel: MainViewModel = hiltViewModel(), finish: () -> Unit) {
    val navController = rememberNavController()

    val isDarkMode = viewModel.themeProvider().shouldUseDarkMode()

    val currentBackStackEntryAsState by navController.currentBackStackEntryAsState()
    val destination = currentBackStackEntryAsState?.destination?.route
        ?: NavGraphs.root.startRoute.route

    if (destination == NavGraphs.root.startRoute.route) {
        BackHandler { finish() }
    }

    JetRortyTheme(darkTheme = isDarkMode) {
        SetupSystemUi(rememberSystemUiController(), JetRortyColors.primary)

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = JetRortyColors.background
        ) {
            DestinationsNavHost(
                navController = navController,
                navGraph = NavGraphs.root,
                dependenciesContainerBuilder = {
                    dependency(AppNavigationProvider(navController))
                }
            )
        }
    }
}