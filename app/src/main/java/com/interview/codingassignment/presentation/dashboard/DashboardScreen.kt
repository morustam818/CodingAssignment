package com.interview.codingassignment.presentation.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.interview.codingassignment.domain.model.MatchingUserRequest
import com.interview.codingassignment.domain.utils.RequestStatus
import com.interview.codingassignment.presentation.ui.theme.Green
import com.interview.codingassignment.presentation.ui.theme.Red
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getUsers()
    }
    Box(
        modifier = modifier.fillMaxSize()
    ){
        viewModel.uiState.value.users?.let {
            LazyRow(
                modifier = modifier.fillMaxSize()
            ){
                items(it) { user ->
                    Box(
                        modifier = Modifier
                            .fillParentMaxWidth()
                            .fillParentMaxHeight()
                    ){
                        DashboardScreenContent(user = user, onClick = { statusRequest,email ->
                            viewModel.updateMatchingRequestStatus(statusRequest,email)
                        })
                    }
                }
            }
        }
    }
}

@Composable
fun DashboardScreenContent(
    modifier: Modifier = Modifier,
    user: MatchingUserRequest,
    onClick: (RequestStatus,String) -> Unit
){
    var isRequestAccepted by remember {
        mutableStateOf(user.status != null)
    }
    ConstraintLayout(
        modifier = modifier.fillMaxSize()
    ) {
        val (ivProfile, cvDetails) = createRefs()
        val topGuideLine = createGuidelineFromTop(0.60f)

        GlideImage(
            modifier = modifier
                .constrainAs(ivProfile) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(topGuideLine)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                },
            imageModel = user.imageUrl,
            contentDescription = "MatchingUserRequest Profile",
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .constrainAs(ivProfile) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(topGuideLine)
                }
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black
                        ),
                        startY = 1000f
                    )
                ),
            contentAlignment = Alignment.BottomStart
        ){
            SetUserInfo(user = user)
        }

        Card(
            modifier = modifier
                .fillMaxHeight(0.28f)
                .constrainAs(cvDetails) {
                    start.linkTo(parent.start, 15.dp)
                    end.linkTo(parent.end, 15.dp)
                    top.linkTo(topGuideLine, 15.dp)
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                },
            elevation = 5.dp
        ) {
            Column(
                modifier = modifier
                    .background(Color.White)
                    .padding(10.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                TextView(
                    text = "About ${user.name}",
                    textSize = 22,
                    textColor = Color.Black,
                    fontWeight = FontWeight.SemiBold
                )
                TextView(
                    text = "Lorem ipsum dolor sit ametostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                    textSize = 14,
                    textColor = Color.Black
                )
                Row(
                    modifier = modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.spacedBy(
                        12.dp,
                        alignment = Alignment.CenterHorizontally
                    ),
                ) {
                    if (isRequestAccepted){
                        user.status?.let {
                            SetRequestStatusText(status = it)
                        }
                    }else{
                        CustomButton(
                            modifier = modifier
                                .fillMaxWidth()
                                .weight(1f),
                            backgroundColor = Green,
                            text = "Accept",
                            textColor = Color.White
                        ) {
                            isRequestAccepted = !isRequestAccepted
                            onClick(RequestStatus.ACCEPTED,user.email)
                        }
                        CustomButton(
                            modifier = modifier
                                .fillMaxWidth()
                                .weight(1f),
                            backgroundColor = Red,
                            text = "Decline",
                            textColor = Color.White
                        ) {
                            isRequestAccepted = !isRequestAccepted
                            onClick(RequestStatus.DECLINED,user.email)
                        }
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun Preview(){
//    DashboardScreenContent()
//}

@Composable
fun SetUserInfo(
    modifier: Modifier = Modifier,
    user: MatchingUserRequest
){
    Column(
        modifier = modifier.padding(start = 25.dp, bottom = 15.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        TextView(
            text = user.name,
            textSize = 22,
            textColor = Color.White,
            fontWeight = FontWeight.SemiBold
        )
        TextView(
            text = user.age.toString(),
            textSize = 14,
            textColor = Color.White.copy(0.8f)
        )
        TextView(
            text = user.address,
            textSize = 14,
            textColor = Color.White.copy(0.8f)
        )
    }
}

@Composable
fun SetRequestStatusText(
    status: RequestStatus
){
    when(status){
        RequestStatus.ACCEPTED ->{
            TextViewWithBorder(
                text = status.value,
                textSize = 23,
                textColor = Green,
                borderColor = Green
            )
        }
        RequestStatus.DECLINED ->{
            TextViewWithBorder(
                text = status.value,
                textSize = 23,
                textColor = Red,
                borderColor = Red
            )
        }
    }
}