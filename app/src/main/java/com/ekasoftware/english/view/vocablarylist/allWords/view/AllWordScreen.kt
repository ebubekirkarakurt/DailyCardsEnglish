package com.ekasoftware.english.view.vocablarylist.allWords.view

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import com.ekasoftware.english.assets.Screen
import com.ekasoftware.english.statusBarColor
import com.ekasoftware.english.ui.theme.CorrectColor
import com.ekasoftware.english.ui.theme.UiColor
import com.ekasoftware.english.ui.theme.WrongColor
import com.ekasoftware.english.view.vocablarylist.allWords.model.Word
import com.ekasoftware.english.view.vocablarylist.vocablarydb.data.Vocablary
import com.ekasoftware.english.view.vocablarylist.vocablarydb.model.VocablaryDatabase
import com.ekasoftware.english.view.vocablarylist.vocablarydb.repository.VocablaryRepository
import com.ekasoftware.english.view.vocablarylist.vocablarydb.viewmodel.VocablaryViewModel
import com.ekasoftware.english.view.vocablarylist.vocablarydb.viewmodel.VocablaryViewModelProviderFactory
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox


@Composable
fun AllWordScreen(
    navController: NavHostController,
    wordList: List<Word>,
    itemIndex: Int
) {
    statusBarColor(statusBarColor = Color.White)

    val vocRepository =
        VocablaryRepository(VocablaryDatabase(LocalContext.current.applicationContext))
    val viewModel: VocablaryViewModel = viewModel(
        factory = VocablaryViewModelProviderFactory(
            app = LocalContext.current.applicationContext
                    as Application,
            vocRepository = vocRepository
        )
    )

    val screenTitle = listOf(
        "Ülkeler", "Yiyecekler", "Yönler",
        "Hava Durumu/Mevsimler", "Günlük Aktivite",
        "Alış-Veriş", "Sağlık", "Alışkanlıklar", "Tatil", "Bonus Kelimeler"
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {

            Button(
                modifier = Modifier
                    .background(Color.Transparent)
                    .padding(horizontal = 5.dp, vertical = 10.dp),
                onClick = { navController.navigate(Screen.VocListScreen.route) },
                colors = ButtonDefaults.outlinedButtonColors(Color.Transparent)
            )
            {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowLeft,
                    contentDescription = "backbutton"
                )
            }

            Text(
                text = screenTitle[itemIndex],
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }


        LazyColumn(
            modifier = Modifier.padding(10.dp)
        ) {
            itemsIndexed(items = wordList) { index, item ->

                val ok = SwipeAction(
                    onSwipe = {
                        val voc = Vocablary(
                            index,
                            item.ENG,
                            item.TR
                        )

                        viewModel.deleteVoc(voc)
                    },
                    icon = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Biliyorum",
                                modifier = Modifier
                                    .padding(5.dp)
                            )

                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = "",
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                    },
                    background = CorrectColor
                )

                val nope = SwipeAction(
                    onSwipe = {
                        val voc = Vocablary(
                            index,
                            item.ENG,
                            item.TR
                        )

                        viewModel.addVoc(voc)

                    },
                    icon = {

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = "",
                                modifier = Modifier.padding(10.dp)
                            )
                            Text(
                                text = "Bilmiyorum",
                                modifier = Modifier
                                    .padding(5.dp)
                            )
                        }

                    },
                    background = WrongColor
                )


                if (item.categoryId == itemIndex + 1) {
                    SwipeableActionsBox(
                        startActions = listOf(ok),
                        endActions = listOf(nope)
                    ) {
                        Card(
                            modifier = Modifier
                                .padding(8.dp, 4.dp)
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .background(Color.Transparent)
                                .shadow(
                                    10.dp,
                                    ambientColor = Color.Black, spotColor = UiColor
                                )
                                .height(110.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White,
                                contentColor = Color.Black
                            )
                        ) {

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                                    .padding(10.dp)
                                    .weight(0.8f)
                            ) {
                                Text(
                                    text = item.ENG,
                                    modifier = Modifier
                                        .align(Alignment.Start)
                                        .padding(10.dp),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 17.sp
                                )
                                Text(
                                    text = item.TR,
                                    modifier = Modifier
                                        .align(Alignment.End)
                                        .padding(4.dp),
                                    fontSize = 13.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewAllWordScreen() {
    AllWordScreen(
        rememberNavController(),
        wordList = listOf(
            Word(
                "Germany",
                "Almanya",
                1,
                2
            ),
            Word(
                "Turkey",
                "Türkiye",
                1,
                2
            ),
            Word(
                "Merhaba benim adom ali cabbar ua senin ismin nedir çok yakışıklısın!",
                "Merhaba benim adom ali cabbar ua senin ismin nedir çok yakışıklısın",
                1,
                2
            )
        ),
        itemIndex = 1
    )
}