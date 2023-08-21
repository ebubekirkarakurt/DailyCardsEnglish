import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.ekasoftware.english.assets.Screen
import com.ekasoftware.english.ui.theme.MyGray
import com.ekasoftware.english.view.booksandstories.books.model.Book
import com.ekasoftware.english.view.booksandstories.stories.model.Story

@Composable
fun Books(
    navController: NavHostController,
    storyList: List<Story>,
    bookList: List<Book>
) {
    Column {
        BooksList(
            navController = navController,
            storyList = storyList,
            bookList = bookList
        )
    }
}

@Composable
fun BooksList(
    navController: NavHostController,
    storyList: List<Story>,
    bookList: List<Book>
) {

    Column(
        modifier = Modifier
            .background(MyGray)
            .fillMaxSize()
    ) {
        Button(
            modifier = Modifier
                .background(Color.Transparent)
                .padding(horizontal = 5.dp, vertical = 10.dp),
            onClick = {
                navController.navigate(Screen.Home.route)
            },
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.Transparent,
                contentColor = Color.Black
            )
        ) {
            Icon(imageVector = Icons.Filled.KeyboardArrowLeft, contentDescription = "backbutton")
        }
/*
        Column(Modifier
            .padding(10.dp)
        ) {

            Text(
                text = "Kitaplar",
                modifier = Modifier.padding(10.dp),
                fontSize = 25.sp,
                fontStyle = FontStyle.Italic
            )

            Row(
                Modifier
                    .background(MyGray)
                    .horizontalScroll(rememberScrollState())
            ) {
                bookList.forEachIndexed { index, book ->
                    Row(
                        modifier = Modifier
                            .background(MyGray)
                            .padding(5.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .background(Color.Transparent)
                                .clickable {
                                    navController.navigate(route = "booksdetails/" + index)
                                }
                        ) {
                            Card(
                                modifier = Modifier
                                    .background(Color.White)
                                    .clip(RoundedCornerShape(10.dp))
                                    .padding(5.dp),
                                colors = CardDefaults.cardColors(Color.Transparent)
                            ) {

                                val bookPainter = rememberImagePainter(data = book.imageResource)

                                Column {
                                    Image(
                                        painter = bookPainter,
                                        modifier = Modifier
                                            .padding(5.dp)
                                            .size(150.dp),
                                        contentDescription = null,
                                        contentScale = ContentScale.Fit
                                    )

                                    Text(
                                        text = book.title,
                                        modifier = Modifier
                                            .padding(3.dp)
                                            .align(Alignment.CenterHorizontally),
                                        fontSize = 15.sp
                                    )
                                }

                            }

                        }
                    }
                }
            }
        }
*/
        Column(Modifier
            .padding(top = 10.dp, bottom = 20.dp, start = 10.dp, end = 10.dp)
        ) {

            Text(
                text = "Okuma Parçaları",
                modifier = Modifier.padding(10.dp),
                fontSize = 25.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Italic
            )

            Column(
                Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .verticalScroll(rememberScrollState())
            ) {
                storyList.forEachIndexed { index, story ->
                    Row(
                        modifier = Modifier
                            .padding(5.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .clickable {
                                    navController.navigate(route = "storydetails/" + index)
                                }
                        ) {
                            Card(
                                modifier = Modifier
                                    .background(Color.White)
                                    .fillMaxWidth(1f)
                                    .padding(5.dp),
                                colors = CardDefaults.cardColors(Color.Transparent)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    if (isConnectedToInternet()) {
                                        val storyPainter =
                                            rememberImagePainter(data = story.imageResource)

                                        Image(
                                            painter = storyPainter,
                                            modifier = Modifier
                                                .padding(5.dp)
                                                .wrapContentSize()
                                                .clip(RoundedCornerShape(5.dp))
                                                .size(150.dp),
                                            contentDescription = null,
                                            contentScale = ContentScale.Crop
                                        )
                                    } else {
                                        Text(
                                            text = "Lütfen internete bağlanınız",
                                            modifier = Modifier
                                                .padding(5.dp),
                                            fontSize = 16.sp
                                        )
                                    }

                                    Text(
                                        text = story.title,
                                        modifier = Modifier
                                            .padding(start = 5.dp),
                                        fontSize = 15.sp
                                    )
                                }

                            }
                        }
                    }
                }
            }
        }
    }
}

fun isConnectedToInternet(): Boolean {
    return true
}
