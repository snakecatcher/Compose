package ru.test.compose2.examples

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun Square(
    modifier: Modifier = Modifier,
    color: Color = Color.Red,
    size: Dp = 100.dp
) {
    Rectangle(modifier, size, size, color)
}
@Composable
fun Rectangle(
    modifier: Modifier = Modifier,
    width: Dp = 100.dp,
    height: Dp = 100.dp,
    color: Color = Color.Red
) {
    Box(modifier.then(
        Modifier
            .size(width, height)
            .background(color)
    ))
}
