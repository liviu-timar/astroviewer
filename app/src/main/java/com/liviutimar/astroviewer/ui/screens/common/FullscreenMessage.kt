package com.liviutimar.astroviewer.ui.screens.common

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.liviutimar.astroviewer.R

@Composable
fun FullscreenMessage(
    @DrawableRes icon: Int,
    @StringRes firstLine: Int,
    @StringRes secondLine: Int? = null,
    onTryAgain: (() -> Unit)? = null,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 35.dp, vertical = 10.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(120.dp)
        )
        Spacer(modifier = Modifier.height(40.dp))
        TextCustomMedium(
            text = stringResource(id = firstLine),
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(15.dp))
        secondLine?.let {
            TextCustom(
                text = stringResource(id = it),
                textAlign = TextAlign.Center,
                lineHeight = 25.sp
            )
        }
        onTryAgain?.let {
            Spacer(modifier = Modifier.height(30.dp))
            ButtonCustom(
                onClick = it,
                label = stringResource(id = R.string.try_again)
            )
        }
    }
}