package com.interview.codingassignment

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.interview.codingassignment.presentation.dashboard.DashboardScreen
import com.interview.codingassignment.presentation.splash.SplashScreen

@Composable
fun CodingAssignmentNavGraph(
    navController: NavHostController = rememberNavController(),
    navActions: CodingAssignmentNavigationActions = remember(navController) {
        CodingAssignmentNavigationActions(navController)
    }
){
    NavHost(navController = navController, startDestination = CodingAssignmentDestinations.DASHBOARD_ROUTE){
        composable(route = CodingAssignmentDestinations.DASHBOARD_ROUTE){
            DashboardScreen(navActions)
        }
        composable(route = CodingAssignmentDestinations.SPLASH_ROUTE){
            SplashScreen(navActions)
        }
    }
}