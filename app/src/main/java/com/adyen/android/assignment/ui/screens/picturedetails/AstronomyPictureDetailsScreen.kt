package com.adyen.android.assignment.ui.screens.picturedetails

import androidx.compose.runtime.Composable
import com.adyen.android.assignment.ui.widgets.TextCustom

@Composable
fun AstronomyPictureDetailsScreen(pictureTitle: String?) {
    TextCustom(text = "$pictureTitle")
}