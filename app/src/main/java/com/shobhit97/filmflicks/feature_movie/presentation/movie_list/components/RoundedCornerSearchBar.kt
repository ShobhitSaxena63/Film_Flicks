package com.shobhit97.filmflicks.feature_movie.presentation.movie_list.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shobhit97.filmflicks.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoundedCornerSearchBar(modifier: Modifier = Modifier, onSearch: (String) -> Unit) {
    val isDarkTheme = isSystemInDarkTheme()
    var text by rememberSaveable { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    TextField(
        value = text,
        leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Search") },
        onValueChange = {
            text = it
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                // Perform search here
                onSearch(text)
                focusManager.clearFocus()
            }

        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor =  if (isDarkTheme) Color.DarkGray else Color.White,
            unfocusedContainerColor =  if (isDarkTheme) Color.DarkGray else Color.White

        ),
        textStyle = TextStyle(
            fontSize = 14.sp,
            textAlign = TextAlign.Left,
            color = if (isDarkTheme) Color.White else Color.Black,
            platformStyle = PlatformTextStyle(includeFontPadding = false)
        ),
        placeholder = {
            Text(
                text = stringResource(id = R.string.search),
                fontSize = 14.sp,
                modifier = Modifier.padding(vertical = 0.dp)
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(40.dp))
            .border(
                BorderStroke(
                    width = 2.dp,
                    color = Color.Black
                ),
                shape = RoundedCornerShape(40.dp)
            )


    )
}