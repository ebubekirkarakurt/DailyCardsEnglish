package com.ekasoftware.english.view.vocablarylist.memorization


import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import com.ekasoftware.english.assets.Screen
import com.ekasoftware.english.ui.theme.CorrectColor
import com.ekasoftware.english.ui.theme.UiColor
import com.ekasoftware.english.ui.theme.WrongColor
import com.ekasoftware.english.view.vocablarylist.vocablarydb.data.Vocablary
import com.ekasoftware.english.view.vocablarylist.vocablarydb.model.VocablaryDatabase
import com.ekasoftware.english.view.vocablarylist.vocablarydb.repository.VocablaryRepository
import com.ekasoftware.english.view.vocablarylist.vocablarydb.viewmodel.VocablaryViewModel
import com.ekasoftware.english.view.vocablarylist.vocablarydb.viewmodel.VocablaryViewModelProviderFactory
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox


@Composable
fun MemorizationScreen(navController: NavHostController) {

    val vocRepository = VocablaryRepository(VocablaryDatabase(LocalContext.current.applicationContext))
    val viewModel: VocablaryViewModel = viewModel(
        factory = VocablaryViewModelProviderFactory(app = LocalContext.current.applicationContext as Application,
            vocRepository = vocRepository)
    )

    var randomVocs by remember { mutableStateOf(emptyList<Vocablary>()) }

    LaunchedEffect(Unit) {
        val randomVocsList = viewModel.getRandomVocs()
        randomVocs = randomVocsList
    }

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
                onClick = { navController.navigate(Screen.Home.route) },
                colors = ButtonDefaults.outlinedButtonColors(Color.Transparent)
            ) {
                Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "backbutton")
            }
        }

        LazyColumn(
            modifier = Modifier.padding(10.dp)
        ) {
            itemsIndexed(items = randomVocs) { item, vocablary ->
                val ok = SwipeAction(
                    onSwipe = {
                        val voc = Vocablary(
                            vocablary.id,
                            vocablary.ENG,
                            vocablary.TR
                        )
                        viewModel.deleteVoc(voc)
                    },
                    icon = {

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ){
                            Text(text = "Biliyorum",
                                modifier = Modifier
                                    .padding(5.dp)
                            )

                            Icon(imageVector = Icons.Filled.Check,
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
                            vocablary.id,
                            vocablary.ENG,
                            vocablary.TR
                        )
                        viewModel.addVoc(voc)
                    },
                    icon = {

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ){
                            Icon(imageVector = Icons.Filled.Close,
                                contentDescription = "",
                                modifier = Modifier.padding(10.dp)
                            )
                            Text(text = "Bilmiyorum",
                                modifier = Modifier
                                    .padding(5.dp)
                            )
                        }

                    },
                    background = WrongColor
                )

                SwipeableActionsBox(
                    startActions = listOf(ok),
                    endActions = listOf(nope)
                ) {
                    Card(
                        modifier = Modifier
                            .padding(8.dp, 4.dp)
                            .fillMaxWidth()
                            .background(Color.Transparent)
                            .shadow(10.dp, ambientColor = Color.Black, spotColor = UiColor)
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
                                .padding(10.dp)
                                .weight(0.8f)
                        ) {
                            Text(
                                text = vocablary.ENG,
                                modifier = Modifier
                                    .align(Alignment.Start)
                                    .padding(10.dp),
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                            Text(
                                text = vocablary.TR,
                                modifier = Modifier
                                    .align(Alignment.End)
                                    .padding(4.dp),
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
