package com.interview.codingassignment

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.interview.codingassignment.common.Constants
import com.interview.codingassignment.presentation.dashboard.DashboardScreen

@Composable
fun CodingAssignmentNavGraph(
    navController: NavHostController = rememberNavController()
){
    NavHost(navController = navController, startDestination = Constants.DASHBOARD_ROUTE){
        composable(route = Constants.DASHBOARD_ROUTE){
            DashboardScreen()
        }
    }
}