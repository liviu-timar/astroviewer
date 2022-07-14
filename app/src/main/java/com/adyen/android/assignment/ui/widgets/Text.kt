package com.adyen.android.assignment.ui.widgets

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.adyen.android.assignment.ui.theme.Roboto

@Composable
fun TextCustom(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 16.sp,
    fontStyle: FontStyle = FontStyle.Normal,
    fontWeight: FontWeight = FontWeight.Normal,
    maxLines: Int = 1
) {
    Text(
        text = text,
        modifier = modifier,
        color = Color.White,
        fontSize = fontSize,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        fontFamily = Roboto,
        maxLines = maxLines,
    )
}

@Composable
fun TextCustomMedium(text: String, maxLines: Int = 1) {
    TextCustom(
        text = text,
        fontWeight = FontWeight.Medium,
        maxLines = maxLines
    )
}

@Composable
fun TextCustomItalic(text: String) {
    TextCustom(
        text = text,
        fontStyle = FontStyle.Italic
    )
}