package com.ekasoftware.english.view.vocablarylist.voclistscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import com.ekasoftware.english.R
import com.ekasoftware.english.assets.Screen

@Composable
fun VocListScreen(navController: NavHostController,) {
    val vocListImages = listOf(
        R.drawable.bayraklar ,R.drawable.meyveler,
        R.drawable.yoltarifi, R.drawable.havadurumu,
        R.drawable.gunlukaktivite,R.drawable.alisveris,
        R.drawable.saglik,R.drawable.rutinler,
        R.drawable.tatilvegezi,
    )

    val vocListTitles = listOf(
        "Ülkeler","Meyve-Sebze", "Yönler", "Hava Durumu/Mevsimler",
        "Günlük Aktivite", "Alış-Veriş", "Sağlık", "Rutinler", "Tatil ve GEzi"
    )

    Column(modifier = Modifier.fillMaxSize(1f)){

        Button(modifier = Modifier
            .background(Color.Transparent)
            .padding(horizontal = 5.dp, vertical = 10.dp),
            onClick = { navController.navigate(Screen.Home.route) },
            colors = ButtonDefaults.outlinedButtonColors(Color.Transparent)
        )
        {
            Icon(imageVector = Icons.Filled.KeyboardArrowLeft, contentDescription = "backbutton")
        }

        LazyVerticalGrid(
            columns = GridCells.Adaptive(110.dp),
            contentPadding = PaddingValues(
                start = 5.dp,
                top = 20.dp,
                end = 5.dp,
                bottom = 10.dp
            ),
            verticalArrangement = Arrangement.spacedBy(1.dp),
            horizontalArrangement = Arrangement.spacedBy(1.dp),
            content = {
                items(vocListTitles.size) { index ->

                    Row (verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center){

                        Box(
                            Modifier
                                .padding(end = 15.dp, bottom = 5.dp)
                                .align(Alignment.CenterVertically)
                                .clickable {
                                    navController.navigate("AllWordScreen/"+ index)
                                }){
                            Image(
                                painter = painterResource(id = vocListImages[index]),
                                contentDescription = "icons",
                                Modifier
                                    .fillMaxSize()
                                    .padding(start = 15.dp, top = 5.dp)
                            )
                        }
                    }

                }
            }
        )
        Image(painter = painterResource(id = R.drawable.reboot),
            modifier = Modifier
                .fillMaxWidth(),
            contentDescription = "")

    }



}
@Preview(showBackground = true)
@Composable
fun VocListScreenPreview() {
    VocListScreen(rememberNavController())
}