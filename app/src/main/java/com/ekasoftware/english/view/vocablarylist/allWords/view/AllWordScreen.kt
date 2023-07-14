package com.ekasoftware.english.view.vocablarylist.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ekasoftware.english.view.vocablarylist.model.Word

@Composable
fun AllWordScreen(wordList :  List<Word>) {

    LazyColumn {
        itemsIndexed(items = wordList) { index, item ->
            Card(
                modifier = Modifier
                    .padding(8.dp, 4.dp)
                    .fillMaxWidth()
                    .height(110.dp), shape = RoundedCornerShape(8.dp)
            ) {

                    Row(
                        Modifier
                            .padding(4.dp)
                            .fillMaxSize()
                    ) {

                        Column(
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxHeight()
                                .weight(0.8f)
                        ) {
                            Text(
                                text = item.ENG,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = item.TR,
                                modifier = Modifier
                                    .background(
                                        Color.LightGray
                                    )
                                    .padding(4.dp)
                            )

                        }
                    }

            }
        }
    }
}