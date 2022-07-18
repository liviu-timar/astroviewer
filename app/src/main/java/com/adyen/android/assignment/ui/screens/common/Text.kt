package com.adyen.android.assignment.ui.screens.common

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    maxLines: Int = Int.MAX_VALUE
) {
    Text(
        text = text,
        modifier = modifier,
        color = Color.White,
        fontSize = fontSize,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        fontFamily = Roboto,
        textAlign = textAlign,
        lineHeight = lineHeight,
        maxLines = maxLines,
    )
}

@Composable
fun TextCustomMedium(
    text: String,
    fontSize: TextUnit = 16.sp,
    lineHeight: TextUnit = TextUnit.Unspecified,
    maxLines: Int = Int.MAX_VALUE,
) {
    TextCustom(
        text = text,
        fontSize = fontSize,
        fontWeight = FontWeight.Medium,
        lineHeight = lineHeight,
        maxLines = maxLines
    )
}