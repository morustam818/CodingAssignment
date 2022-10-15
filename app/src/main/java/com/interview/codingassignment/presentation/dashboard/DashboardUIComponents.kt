package com.interview.codingassignment.presentation.dashboard

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextView(
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Normal,
    text : String,
    textColor : Color = Color.White,
    textSize : Int
){
    Text(
        modifier = modifier,
        text = text,
        fontSize = textSize.sp,
        color = textColor,
        fontWeight = fontWeight
    )
}

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    backgroundColor : Color = Color.Transparent,
    text : String,
    textColor : Color = Color.White,
    onclick : () -> Unit
){
    Button(
        modifier = modifier,
        onClick = { onclick() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(
            text = text,
            color = textColor
        )
    }
}

@Composable
fun TextViewWithBorder(
    modifier: Modifier = Modifier,
    borderWidth : Int = 1,
    borderColor : Color = Color.Transparent,
    text : String,
    textColor : Color = Color.White,
    textSize : Int
){
    Text(
        modifier = modifier
            .border(
                borderWidth.dp,
                borderColor,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(25.dp,6.dp),
        text = text,
        fontSize = textSize.sp,
        color = textColor,
    )
}