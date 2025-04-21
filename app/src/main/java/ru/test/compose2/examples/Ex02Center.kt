package ru.test.compose2.examples

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
@Preview(showSystemUi = true)
fun Ex02Center(){
    ConstraintLayout{
        Square(
            modifier = Modifier.constrainAs(createRef()){
                centerTo(parent)
            }
        )
        Square(
            color = Color.Red,
            size = 200.dp,
            modifier = Modifier.constrainAs(createRef()){
                centerTo(parent)
            }
        )
    }
}