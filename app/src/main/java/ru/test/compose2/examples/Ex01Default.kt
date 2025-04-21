package ru.test.compose2.examples

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
@Preview(showSystemUi = true)

fun Ex01Default(){
    ConstraintLayout{
        Square(
            color = Color.Red,
            size = 200.dp
        )
        Square(
            color = Color.Blue,
            size = 150.dp
        )
        Square(
            color = Color.Green,
            size = 100.dp
        )
    }
}