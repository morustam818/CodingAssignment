package com.interview.codingassignment

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.interview.codingassignment.CodingAssignmentScreens.DASHBOARD_SCREEN
import com.interview.codingassignment.CodingAssignmentScreens.SPLASH_SCREEN


private object CodingAssignmentScreens {
    const val SPLASH_SCREEN = "splash_screen"
    const val DASHBOARD_SCREEN = "dashboard_screen"
}

//object CodingAssignmentDestinationsArgs {
//}

object CodingAssignmentDestinations {
    const val SPLASH_ROUTE = SPLASH_SCREEN
    const val DASHBOARD_ROUTE = DASHBOARD_SCREEN
}

class CodingAssignmentNavigationActions(private val navController: NavHostController) {

    fun navigateToDashboard() {
        navController.navigate(
            CodingAssignmentDestinations.DASHBOARD_ROUTE
        ) {
            popUpTo(navController.graph.findStartDestination().id) {
                inclusive = true
            }
            launchSingleTop = true
        }
    }
}