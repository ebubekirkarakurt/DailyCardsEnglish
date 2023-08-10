package com.ekasoftware.english.view.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ekasoftware.english.R

/*

@Composable
fun Settings(navController : NavHostController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()

        )
        {
            Box(modifier = Modifier.padding(end = 16.dp))
            {

               Row( horizontalArrangement = Arrangement.Center,
                   verticalAlignment = Alignment.CenterVertically) {

                   Image(
                       painter = painterResource(id = R.drawable.ezber),
                       contentDescription = "settings",
                       alignment = Alignment.CenterEnd
                   )

                   Text(
                       text = "Ayarlar",
                       fontSize = 30.sp,
                       fontStyle = FontStyle.Italic
                   )
               }
            }
        }
        Column {
            SettingsItems()
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsItems() {
    val items = listOf<String>(
        "Dil Ayalarları",
        "Bildirimler",
        "Koala Bot'a bi kahve :)",
        "Hakkımızda",
    )

    val icons = listOf<Int>(
        R.drawable.swap,
        R.drawable.swap,
        R.drawable.swap,
        R.drawable.swap,
    )


    Column{

        items.forEachIndexed{ index, string ->

            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
                colors = CardDefaults.outlinedCardColors(Color.Transparent),){

                Box(modifier = Modifier
                    .fillMaxWidth())
                {
                    Row(
                        horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    {

                        when(string){

                            "Dil Ayalarları"->
                                Box {
                                    LanguageDropDown()
                                }

                            "Bildirimler" ->

                                Box(){
                                    Row(horizontalArrangement = Arrangement.Absolute.Center,
                                        verticalAlignment = Alignment.CenterVertically,){
                                        Icon(
                                            painter = painterResource(id = icons[index]),
                                            contentDescription = "settingsicon",
                                            modifier = Modifier.padding(10.dp))

                                        Text(text = string,
                                            modifier = Modifier
                                                .padding(10.dp),
                                            fontSize = 20.sp)
                                    }
                                }

                            "Koala Bot'a bi kahve :)" ->

                                Box(){
                                    Row(horizontalArrangement = Arrangement.Absolute.Center,
                                        verticalAlignment = Alignment.CenterVertically,){
                                        Icon(
                                            painter = painterResource(id = icons[index]),
                                            contentDescription = "settingsicon",
                                            modifier = Modifier.padding(10.dp))

                                        Text(text = string,
                                            modifier = Modifier
                                                .padding(10.dp),
                                            fontSize = 20.sp)
                                    }
                                }

                            "Hakkımızda" ->

                                Box(){
                                    Row(horizontalArrangement = Arrangement.Absolute.Center,
                                        verticalAlignment = Alignment.CenterVertically,){
                                        Icon(
                                            painter = painterResource(id = icons[index]),
                                            contentDescription = "settingsicon",
                                            modifier = Modifier.padding(10.dp))

                                        Text(text = string,
                                            modifier = Modifier
                                                .padding(10.dp),
                                            fontSize = 20.sp)
                                    }
                                }

                        }
                        Row (horizontalArrangement = Arrangement.End){
                            Icon(imageVector = Icons.Filled.KeyboardArrowRight,
                                contentDescription = "details",
                                modifier = Modifier.padding(15.dp)
                            )
                        }

                    }
                }
            }
        }
    }
}


@ExperimentalMaterial3Api
@Composable
fun LanguageDropDown() {

    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(CircleShape)
            .padding(15.dp)
            .clickable { expanded = true },
        colors = CardDefaults.outlinedCardColors(Color.Transparent),
        onClick = { expanded = !expanded }
    )
    {
        Row(horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()){

            Row {
                Icon(
                    painter = painterResource(id = R.drawable.swap),
                    contentDescription = "settingsicon",
                    modifier = Modifier.padding(end = 8.dp)
                )

                Text(
                    text = "Dil Ayarları",
                    modifier = Modifier
                        .padding(10.dp),
                    fontSize = 20.sp
                )
            }

            Row(horizontalArrangement = Arrangement.End){
                Icon(imageVector = Icons.Filled.KeyboardArrowRight,
                    contentDescription = "details",
                    modifier = Modifier.padding(15.dp)
                )
            }
        }

    }

    if (expanded) {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.wrapContentWidth(),
        ) {
            DropdownMenuItem(text = { Text(text = "English") }, onClick = { /*TODO*/ })

            DropdownMenuItem(text = { Text(text = "Türkçe") }, onClick = { /*TODO*/ })
        }
    }
}

@Preview
@Composable
fun Preview() {
    Settings(navController = rememberNavController())
}

 */