package com.liviutimar.astroviewer.ui.utils

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.liviutimar.astroviewer.domain.models.Picture

class PreviewPictureProvider : PreviewParameterProvider<Picture> {
    override val values: Sequence<Picture>
        get() = sequenceOf(
            Picture(
                id = 1,
                title = "Recycling Cassiopeia A",
                date = "12 Jul 2002",
                desc = "",
                url = "https://apod.nasa.gov/apod/image/0207/casA_hst.jpg"
            )
        )

}

class PreviewPictureListProvider : PreviewParameterProvider<List<Picture>> {
    override val values: Sequence<List<Picture>>
        get() = sequenceOf(
            listOf(
                Picture(
                    id = 1,
                    title = "Recycling Cassiopeia A",
                    date = "12 Jul 2002",
                    desc = "",
                    url = "https://apod.nasa.gov/apod/image/0207/casA_hst.jpg"
                ),
                Picture(
                    id = 2,
                    title = "Simulated Supergiant Star",
                    date = "22 Dec 2000",
                    desc = "",
                    url = "https://apod.nasa.gov/apod/image/0012/supergiantsim_freytag.jpg"
                )
            )
        )
}