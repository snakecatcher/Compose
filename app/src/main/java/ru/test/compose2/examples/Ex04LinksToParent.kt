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
fun Ex04LinksToParent(){
    ConstraintLayout(Modifier.fillMaxSize()){
        Square(
            color = Color.Red,
            modifier = Modifier.constrainAs(createRef()){
                start.linkTo(parent.start)
                top.linkTo(parent.top)
            }
        )
        Square(
            color = Color.Blue,
            modifier = Modifier.constrainAs(createRef()){
                end.linkTo(parent.end)
                top.linkTo(parent.top)
            }
        )
        Square(
            color = Color.Cyan,
            modifier = Modifier.constrainAs(createRef()){
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom)
            }
        )
        Square(
            color = Color.Green,
            modifier = Modifier.constrainAs(createRef()){
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
        )
    }
}