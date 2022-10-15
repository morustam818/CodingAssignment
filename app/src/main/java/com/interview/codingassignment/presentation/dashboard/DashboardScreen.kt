package com.interview.codingassignment.presentation.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.interview.codingassignment.CodingAssignmentNavigationActions
import timber.log.Timber

@Composable
fun DashboardScreen(
    navActions: CodingAssignmentNavigationActions,
    modifier: Modifier = Modifier,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    Timber.e("Dashboard")
//    LaunchedEffect(Unit) {
//        viewModel.getUsers()
//    }
    Box{
        ConstraintLayout(
            modifier = modifier.fillMaxSize()
        ) {
            val (cvProfile, cvDetails) = createRefs()
            val topGuideLine = createGuidelineFromTop(0.6f)
            Card(
                modifier = modifier
                    .background(Color.Cyan)
                    .constrainAs(cvProfile) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(topGuideLine)
                        width = Dimension.matchParent
                        height = Dimension.matchParent
                    }
            ) {
                Text(text = "Hello", modifier = modifier.fillMaxSize().background(Color.Magenta))
            }

            Card(
                modifier = modifier
                    .constrainAs(cvDetails) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(topGuideLine)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    }
                    .background(Color.Magenta)
            ) {
                Text(text = "Hello", modifier = modifier.fillMaxSize().background(Color.Cyan))
            }
        }
    }
}