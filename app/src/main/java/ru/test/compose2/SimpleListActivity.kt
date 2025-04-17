package ru.test.compose2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MailOutline
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import ru.test.compose2.base.Container

class SimpleListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleAppScreen()

        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SimpleAppScreen() {
    AppScreen()

    /*

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        VectorIcon()
        MaterialIcon()
        TintedVectorIcon()
        IconWithModifier()
        SimpleImage()
        ContentScaleCropImage()
        SquareImage()
        ClippedImage()
        ClippedWithCustomShapeImage()
        SimpleAsyncLoadedImage()
        AsyncLoadedImage()
*/

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AppScreen() {
    val list = remember {
        List(100) { index -> "Simple Text Item #${index + 1}" }

    }
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        stickyHeader {
            ListHeader(text = "List 1")
        }

        item {
            Text(
                text = "HeaderList",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(all = 16.dp)
            )
        }

        items(list) { item ->
            ListItem(text = item)
        }

        item {
            Text(
                text = "Footer",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(all = 16.dp)
            )
        }
        stickyHeader {
            ListHeader(text = "List 2")
        }

        items(30) { index ->
            ListItem(text = "Index: $index")
        }


    }
    /*
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Container(name = "Button Example") {
            ButtonExample()
        }
        Container(name = "TextField Example") {
            TextFieldExample()
        }
        Container(name = "CheckBox Example") {
            CheckBoxExample()
        }

    }

     */
}

@Composable
fun ListHeader(text: String) {
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(all = 16.dp)
    )
}

@Composable
fun ListItem(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(all = 16.dp)
    )
}

enum class RequestState {
    LOAD_NOT_REQUESTED,
    LOAD_REQUESTED
}

@Composable
fun AsyncLoadedImage() {
    var state by remember {
        mutableStateOf(RequestState.LOAD_NOT_REQUESTED)
    }
    Container(name = "AsyncLoadedImage") {
        when (state) {
            RequestState.LOAD_NOT_REQUESTED -> LoadButton { state = RequestState.LOAD_REQUESTED }
            RequestState.LOAD_REQUESTED -> {
                SubcomposeAsyncImage(
                    model = "https://i.pinimg.com/originals/e2/ec/31/e2ec318333608d137b47ce8f2a83eed0.jpg",
                    contentDescription = null,
                    loading = { CircularProgressIndicator() },
                    error = {
                        Text(text = "Load Failed", color = Color.Red)
                    }
                )
            }
        }
    }
}

@Composable
private fun LoadButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = "Load")

    }
}

@Composable
fun SimpleAsyncLoadedImage() {
    Container(name = "SimpleAsyncLoadedImage") {
        AsyncImage(
            model = "https://i.pinimg.com/originals/e2/ec/31/e2ec318333608d137b47ce8f2a83eed0.jpg",
            contentDescription = null
        )
    }
}

@Composable
fun ClippedWithCustomShapeImage() {
    Container(name = "ClippedWithCustomShapeImage") {
        Image(
            painter = painterResource(id = R.drawable.vooodo_detective),
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(R.string.voodo_detective),
            modifier = Modifier
                .aspectRatio(1f / 1f)
                .clip(GenericShape { size, _ ->
                    moveTo(0f, size.height)
                    lineTo(size.width / 2, 0f)
                    lineTo(size.width, size.height)
                })
        )
    }
}

@Composable
fun ClippedImage() {
    Container(name = "ClippedImage") {
        Image(
            painter = painterResource(id = R.drawable.vooodo_detective),
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(R.string.voodo_detective),
            modifier = Modifier
                .aspectRatio(1f / 1f)
                .clip(CircleShape)
        )
    }
}

@Composable
fun SquareImage() {
    Container(name = "SquareImage") {
        Image(
            painter = painterResource(id = R.drawable.vooodo_detective),
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(R.string.voodo_detective),
            modifier = Modifier.aspectRatio(1f / 1f)
        )
    }
}

@Composable
fun ContentScaleCropImage() {
    Container(name = "ContentScaleCropImage") {
        Image(
            painter = painterResource(id = R.drawable.vooodo_detective),
            contentScale = ContentScale.Fit,
            contentDescription = stringResource(R.string.voodo_detective),
        )
    }
}

@Composable
fun SimpleImage() {
    Container(name = "Simple image") {
        Image(
            painter = painterResource(id = R.drawable.vooodo_detective),
            contentDescription = stringResource(R.string.voodo_detective)
        )
    }
}

@Composable
fun IconWithModifier() {
    Container(name = "Vector with modifiers") {
        Icon(
            painter = painterResource(id = R.drawable.icon_task_cargo),
            contentDescription = stringResource(R.string.material_mail_icon),
            modifier = Modifier
                .size(48.dp)
                .background(Color.LightGray, CircleShape)
                .padding(8.dp),
        )
    }
}

@Composable
fun TintedVectorIcon() {
    Container(name = "Material Tint icon") {
        Icon(
            painter = painterResource(id = R.drawable.icon_task_cargo),
            contentDescription = stringResource(R.string.material_mail_icon),
            modifier = Modifier.size(48.dp),
            tint = Color.Red
        )
    }
}

@Composable
fun MaterialIcon() {
    Container(name = "Material icon") {
        Icon(
            imageVector = Icons.Rounded.MailOutline,
            contentDescription = stringResource(R.string.material_mail_icon),
            modifier = Modifier.size(48.dp)
        )
    }
}

@Composable
fun VectorIcon() {
    Container(name = "Simple vector icon") {
        Icon(
            painter = painterResource(id = R.drawable.icon_task_cargo),
            contentDescription = "Cargo Icon"
        )
    }
}
