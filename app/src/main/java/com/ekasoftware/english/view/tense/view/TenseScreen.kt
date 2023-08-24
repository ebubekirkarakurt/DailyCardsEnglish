package com.ekasoftware.english.view.tense.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import com.ekasoftware.english.assets.Screen
import com.ekasoftware.english.statusBarColor
import com.ekasoftware.english.ui.theme.TenseColor1
import com.ekasoftware.english.ui.theme.TenseColor2
import com.ekasoftware.english.ui.theme.TenseColor3
import com.ekasoftware.english.ui.theme.TenseColor4
import com.ekasoftware.english.ui.theme.TenseColor5
import com.ekasoftware.english.view.tense.model.Tense

@Composable
fun TenseScreen(navController: NavHostController, tenseList: List<Tense>) {

    var selectedTenseIndex by remember { mutableStateOf(-1) }

    val colors = listOf(
        TenseColor1,
        TenseColor2,
        TenseColor3,
        TenseColor4,
        TenseColor5,
    )

    statusBarColor(statusBarColor = Color.White)

    Column {

        Button(modifier = Modifier
            .background(Color.Transparent)
            .padding(horizontal = 5.dp, vertical = 10.dp),
            onClick = { navController.navigate(Screen.Home.route) },
            colors = ButtonDefaults.outlinedButtonColors(Color.Transparent)
        )
        {
            Icon(imageVector = Icons.Filled.KeyboardArrowLeft, contentDescription = "backbutton")
        }


        Column(
            Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            tenseList.forEachIndexed { index, item ->
                Card(
                    Modifier
                        .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                        .fillMaxWidth()
                        .requiredHeight(40.dp)
                        .background(Color.Transparent)
                        .clickable {
                            selectedTenseIndex = if (selectedTenseIndex == index) -1 else index
                        },
                    shape =
                    if (selectedTenseIndex == index) RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                    else RoundedCornerShape(8.dp)
                    ,
                    colors = CardDefaults.cardColors(
                        containerColor = colors[index % colors.size]
                    )

                ) {
                    Text(
                        text = item.tense,
                        modifier = Modifier.padding(start = 10.dp),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp
                    )
                }
                if (selectedTenseIndex == index) {

                    Card(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .fillMaxWidth()
                            .background(Color.Transparent),
                        shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = colors[index % colors.size]
                        )
                    ) {
                        Column(
                            Modifier
                                .background(colors[index % colors.size])
                                .padding(5.dp)
                        ) {

                            Column (
                                Modifier.padding(5.dp)
                            ){
                                Text(
                                    text = "Pozitif cümle kullanımı:" ,
                                    modifier = Modifier.padding(5.dp),
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 16.sp
                                )
                                Text(text = item.positive,
                                    modifier = Modifier.padding(3.dp),
                                    fontSize = 16.sp
                                )

                            }

                            Column(
                                Modifier.padding(5.dp)
                            ) {
                                Text(
                                    text = "Negatif cümle kullanımı:" ,
                                    modifier = Modifier.padding(5.dp),
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 16.sp
                                )
                                Text(text = item.negative,
                                    modifier = Modifier.padding(3.dp),
                                    fontSize = 16.sp
                                )

                            }


                            Column(
                                Modifier.padding(5.dp)
                            ) {
                                Text(
                                    text = "Soru şeklinde kullanımı:" ,
                                    modifier = Modifier.padding(5.dp),
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 16.sp
                                )
                                Text(text = item.question,
                                    modifier = Modifier.padding(3.dp),
                                    fontSize = 16.sp
                                )

                            }


                            Column(
                                Modifier.padding(5.dp)
                            ) {
                                Text(
                                    text = "Hangi durumlarda kullanılır?" ,
                                    modifier = Modifier.padding(5.dp),
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 16.sp
                                )
                                Text(text = item.situation,
                                    modifier = Modifier.padding(3.dp),
                                    fontSize = 16.sp
                                )

                            }
                        }
                    }
                }
            }
        }
    }
}
