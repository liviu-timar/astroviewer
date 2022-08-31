package com.liviutimar.astroviewer.ui.utils

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.liviutimar.astroviewer.ui.screens.picturelist.PictureListItemUiState

class PreviewPictureProvider : PreviewParameterProvider<PictureListItemUiState> {
    override val values: Sequence<PictureListItemUiState>
        get() = sequenceOf(
            PictureListItemUiState(
                id = 1,
                title = "Recycling Cassiopeia A",
                date = "12 Jul 2002",
                url = "https://apod.nasa.gov/apod/image/0207/casA_hst.jpg"
            )
        )

}

class PreviewPictureListProvider : PreviewParameterProvider<List<PictureListItemUiState>> {
    override val values: Sequence<List<PictureListItemUiState>>
        get() = sequenceOf(
            listOf(
                PictureListItemUiState(
                    id = 1,
                    title = "Recycling Cassiopeia A",
                    date = "12 Jul 2002",
                    url = "https://apod.nasa.gov/apod/image/0207/casA_hst.jpg"
                ),
                PictureListItemUiState(
                    id = 2,
                    title = "Simulated Supergiant Star",
                    date = "22 Dec 2000",
                    url = "https://apod.nasa.gov/apod/image/0012/supergiantsim_freytag.jpg"
                )
            )
        )
}