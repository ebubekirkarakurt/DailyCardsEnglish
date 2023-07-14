package com.ekasoftware.english.view.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@Composable
fun Settings(navController: NavHostController) {
        Column {
                ThemeSettings()
        }
}

@Composable
fun ThemeSettings() {
        val context = LocalContext.current
        val expanded = remember { mutableStateOf(false) }

        Card(modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(1f)
                .clickable {
                        expanded.value = !expanded.value
                }) {
                Box(contentAlignment = Alignment.CenterEnd) {
                        Text(text = "Dil Seçenekleri")
                        DropdownMenu(
                                expanded = expanded.value,
                                onDismissRequest = { expanded.value = false },
                                modifier = Modifier.width(IntrinsicSize.Max)
                        ) {

                                DropdownMenuItem(text = { Text(text = "Açık Tema") },
                                        onClick = { /*TODO*/ },
                                        leadingIcon = {
                                                Icon(
                                                        imageVector = Icons.Filled.Refresh,
                                                        contentDescription = null
                                                )
                                        })

                                DropdownMenuItem(text = { Text(text = "Koyu Tema") },
                                        onClick = { /*TODO*/ },
                                        leadingIcon = {
                                                Icon(
                                                        imageVector = Icons.Filled.Refresh,
                                                        contentDescription = null
                                                ) })
                        }

                }
        }
}

@Preview(showBackground = true)
@Composable
fun SettingsPreview() {
        val navController : NavHostController
        navController = rememberNavController()
        Settings(navController = navController)
}