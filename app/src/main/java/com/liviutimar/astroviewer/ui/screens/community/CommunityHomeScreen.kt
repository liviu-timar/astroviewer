package com.liviutimar.astroviewer.ui.screens.community

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.liviutimar.astroviewer.R
import com.liviutimar.astroviewer.ui.screens.common.CustomTopAppBar
import com.liviutimar.astroviewer.ui.screens.common.FullscreenMessage

@Composable
fun CommunityHomeScreen(modifier: Modifier = Modifier) {
    Column {
        CustomTopAppBar(title = stringResource(id = R.string.community))

        FullscreenMessage(
            icon = R.drawable.ic_community_filled_white,
            firstLine = R.string.welcome_to_community,
            secondLine = R.string.great_things_will_happen
        )
    }
}