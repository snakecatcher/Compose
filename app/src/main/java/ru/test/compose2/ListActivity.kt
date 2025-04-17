package ru.test.compose2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.github.javafaker.Faker
import java.util.Random


data class User(
    val id: Long,
    val photoUrl: String,
    val name: String,
    val status: String
)

fun createUserList(): List<User> {
    val faker = Faker.instance(Random(0))
    val images = mutableListOf(
        "https://i.pinimg.com/originals/e2/ec/31/e2ec318333608d137b47ce8f2a83eed0.jpg",
        "https://i.pinimg.com/736x/e8/8e/4f/e88e4fcb0aabdab3afe9718dd4c62534.jpg",
        "https://i.pinimg.com/550x/83/44/56/834456a24fd26a5a7bbf7774166ca26c.jpg",
        "https://img.goodfon.com/original/3888x2592/1/e4/devushka-krasivaya-grud-poza.jpg",
        "https://www.nastol.com.ua/download.php?img=201402/1680x1050/nastol.com.ua-87675.jpg",
        "https://avatars.mds.yandex.net/i?id=a936766ecc1ae1869678f0b9a80f501b_l-5478998-images-thumbs&n=13",
        "https://avatars.mds.yandex.net/i?id=bc08e900c32f534cfcf3adedb323c43f_l-5235401-images-thumbs&n=13"

    )
    val list = List(100) { index ->
        val id = index + 1L
        User(
            id = id,
            photoUrl = images[index % images.size],
            name = faker.name().fullName(),
            status = faker.shakespeare().hamletQuote()
        )
    }
    return list
}

class ListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListScreen()
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ListScreen() {
    var userList by remember {
        mutableStateOf(createUserList())
    }
    val context = LocalContext.current
    LazyColumn(contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        items(
            items = userList,
            key = { user -> user.id }
        ) { user ->
            UserCard(
                user = user,
                onUserClicked = {
                    Toast.makeText(
                        context,
                        context.getString(R.string.clicked_on, user.name),
                        Toast.LENGTH_SHORT
                    ).show()
                },
                onUserDeleted = { userList -= user },
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

@Composable
fun UserCard(
    user: User,
    onUserClicked: () -> Unit,
    onUserDeleted: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(size = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = rememberRipple()
        ) { onUserClicked }
    ) {
        Row(
            modifier = Modifier.padding(all = 8.dp)
        ) {
            UserImage(url = user.photoUrl)
            Spacer(modifier = Modifier.width(16.dp))
            UserInfo(user = user)
            DeleteUserButton(onClick = onUserDeleted)
        }
    }
}

@Composable
private fun UserImage(url: String) {
    val placeholder = rememberVectorPainter(image = Icons.Rounded.AccountCircle)
    AsyncImage(
        model = url,
        placeholder = placeholder,
        contentDescription = stringResource(id = R.string.user_photo),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(64.dp)
            .aspectRatio(1f / 1f)
            .clip(CircleShape)
    )
}

@Composable
private fun RowScope.UserInfo(user: User) {
    Column(modifier = Modifier.weight(1f)) {
        Text(
            text = user.name,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(text = user.status, fontSize = 16.sp, maxLines = 2, overflow = TextOverflow.Ellipsis)
    }
}

@Composable
private fun RowScope.DeleteUserButton(onClick: () -> Unit) {
    IconButton(onClick = onClick, modifier = Modifier.align(Alignment.CenterVertically)) {
        Icon(
            imageVector = Icons.Rounded.Delete,
            contentDescription = stringResource(id = R.string.delete_user)
        )
    }
}