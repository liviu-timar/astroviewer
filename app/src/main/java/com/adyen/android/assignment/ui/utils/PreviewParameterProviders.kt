package com.adyen.android.assignment.ui.utils

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.adyen.android.assignment.ui.screens.picturelist.models.PictureListItem

class PreviewPictureProvider : PreviewParameterProvider<PictureListItem> {
    override val values: Sequence<PictureListItem>
        get() = sequenceOf(
            PictureListItem(
                id = 1,
                title = "Recycling Cassiopeia A",
                date = "12 Jul 2002",
                url = "https://apod.nasa.gov/apod/image/0207/casA_hst.jpg"
            )
        )

}

class PreviewPictureListProvider : PreviewParameterProvider<List<PictureListItem>> {
    override val values: Sequence<List<PictureListItem>>
        get() = sequenceOf(
            listOf(
                PictureListItem(
                    id = 1,
                    title = "Recycling Cassiopeia A",
                    date = "12 Jul 2002",
                    url = "https://apod.nasa.gov/apod/image/0207/casA_hst.jpg"
                ),
                PictureListItem(
                    id = 2,
                    title = "Simulated Supergiant Star",
                    date = "22 Dec 2000",
                    url = "https://apod.nasa.gov/apod/image/0012/supergiantsim_freytag.jpg"
                )
            )
        )
}