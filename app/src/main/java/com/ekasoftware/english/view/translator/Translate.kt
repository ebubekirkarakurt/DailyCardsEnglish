@file:OptIn(ExperimentalMaterial3Api::class)

package com.ekasoftware.english.view.translator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import com.ekasoftware.english.R
import com.ekasoftware.english.assets.Screen
import com.ekasoftware.english.statusBarColor
import com.ekasoftware.english.ui.theme.GrayLight
import com.ekasoftware.english.view.translator.viewmodel.TranslatorViewModel


@Composable
fun Translate(navController: NavHostController, viewModel: TranslatorViewModel) {

    Column(modifier = Modifier.fillMaxSize()) {
        TranslateComment(navController = navController, viewModel = viewModel)
        TranslateLanguage(viewModel)
        TranslateResult(viewModel = viewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TranslateComment(navController: NavHostController,viewModel: TranslatorViewModel) {

    val state = viewModel.state.value
    val context = LocalContext.current

    statusBarColor(statusBarColor = Color.White)

    Row(modifier = Modifier
        .padding(horizontal = 0.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    )
    {

        Button(modifier = Modifier
            .background(Color.Transparent)
            .padding(horizontal = 5.dp, vertical = 10.dp),
            onClick = { navController.navigate(Screen.Home.route) },
            colors = ButtonDefaults.outlinedButtonColors(Color.Transparent)
        )
        {
            Icon(imageVector = Icons.Filled.KeyboardArrowLeft, contentDescription = "backbutton")
        }

        Text(modifier = Modifier
            .padding(5.dp),
            text = "OLA  ~  English Translator"
        )
    }

    Column(modifier = Modifier
        .padding(horizontal = 20.dp, vertical = 20.dp)
        .wrapContentWidth()
        .clip(RoundedCornerShape(15.dp))
        ) {

        Card( modifier = Modifier
            .background(Color.Transparent),
            colors = CardDefaults.outlinedCardColors(
                contentColor = Color.Black,
                containerColor = GrayLight
            )
        )
            {
            OutlinedTextField(
                value = state.textToBeTranslated,
                onValueChange = { viewModel.onTextToBeTranslatedChange(it) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                ),
                maxLines = 20,
                placeholder = { Text(text = "Metin Girin")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .height(150.dp)
            )

            Button(
                modifier = Modifier
                    .padding(15.dp)
                    .align(Alignment.End),
                onClick = {
                    viewModel.onTranslateButtonClick(
                        text = state.textToBeTranslated,
                        context = context
                    )
                },
                colors = ButtonDefaults.buttonColors(
                    Color.Transparent
                ),
                enabled = state.isButtonEnabled,
            ) {
                Text(text = "Çevir")
            }
        }

    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TranslateResult(viewModel: TranslatorViewModel) {

    val state = viewModel.state.value

    Column(modifier = Modifier
        .padding(20.dp)
        .wrapContentWidth()
        .clip(RoundedCornerShape(15.dp))
    )
    {

        Card( modifier = Modifier
            .background(Color.Transparent),
            colors = CardDefaults.outlinedCardColors(
                contentColor = Color.Black,
                containerColor = GrayLight
            )

        ){
            OutlinedTextField(
                value = state.translatedText,
                onValueChange = { viewModel.onTextToBeTranslatedChange(it) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                ),
                maxLines = 20,
                placeholder = { Text(text = "Çeviri Sonucu")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .height(180.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TranslateLanguage(viewModel: TranslatorViewModel) {

    var expanded by remember { mutableStateOf(false) }
    val languageOptions = listOf("Türkçe", "İngilizce")
    var selectedLanguage by remember { mutableStateOf(languageOptions[0]) }
    var selectedSecondLanguage by remember { mutableStateOf(languageOptions[1]) }


    Card(modifier = Modifier
        .padding(horizontal = 7.dp, vertical = 8.dp),
        colors = CardDefaults.outlinedCardColors(
            Color.Transparent
        )
    )
    {
        Row {

            Row {

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = it }
                ) {
                    OutlinedTextField(
                        value = selectedLanguage,
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier
                            .width(170.dp)
                            .padding(horizontal = 25.dp, vertical = 0.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent
                        )
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    )
                    {
                        languageOptions.forEach { language ->
                            DropdownMenuItem(
                                text = { Text(text = language)},
                                onClick = {
                                    selectedLanguage = language
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }

            SwitchButton(selectedLanguage = selectedLanguage,
                selectedSecondLanguage = selectedSecondLanguage,
                onLanguageSwitched = { language1, language2 ->
                    selectedLanguage = language2
                    selectedSecondLanguage = language1
                },
                viewModel = viewModel
            )

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = it }
            ) {
                OutlinedTextField(
                    value = selectedSecondLanguage,
                    onValueChange = {},
                    readOnly = true,
                    modifier = Modifier
                        .width(170.dp)
                        .padding(horizontal = 25.dp, vertical = 0.dp),
                    placeholder = { Text(text = "Dil Seçin") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    )
                )
                ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    languageOptions.forEach { language ->
                        DropdownMenuItem(
                            text = { Text(text = language)},
                            onClick = {
                                selectedSecondLanguage = language
                                expanded = false
                            }
                        )
                    }
                }
            }
        }
    }



}

@Composable
fun SwitchButton(
    selectedLanguage: String,
    selectedSecondLanguage: String,
    onLanguageSwitched: (String, String) -> Unit,
    viewModel : TranslatorViewModel
)
{

    var switchOn by remember { mutableStateOf(false) }


    Button(
        onClick = {
        switchOn = !switchOn
        onLanguageSwitched(selectedLanguage, selectedSecondLanguage)
        viewModel.onSwitchButtonClick()
    },
        colors = ButtonDefaults.outlinedButtonColors(
            Color.Transparent
        )
    )
    {
        Icon(painter = painterResource(id = R.drawable.swap),
            contentDescription = "button")
    }
}
