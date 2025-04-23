package ru.test.compose2.examples

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
@Preview(showSystemUi = true)
fun Ex03Center(){
    ConstraintLayout(Modifier.fillMaxSize()){
        Square(
            modifier = Modifier.constrainAs(createRef()){
                centerVerticallyTo(parent)
            }
        )
        Square(
            color = Color.Blue,
            modifier = Modifier.constrainAs(createRef()){
                centerHorizontallyTo(parent)
            }
        )
    }
}