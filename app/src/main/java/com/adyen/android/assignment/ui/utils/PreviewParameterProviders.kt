package com.adyen.android.assignment.ui.utils

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.adyen.android.assignment.domain.models.AstronomyPicture
import java.time.LocalDate

class PreviewPictureProvider : PreviewParameterProvider<AstronomyPicture> {
    override val values: Sequence<AstronomyPicture>
        get() = sequenceOf(
            AstronomyPicture(
                id = 1,
                title = "Recycling Cassiopeia A",
                desc = "For billions of years massive stars in our Milky Way Galaxy have lived spectacular lives.",
                date = LocalDate.parse("2002-07-12"),
                url = "https://apod.nasa.gov/apod/image/0207/casA_hst.jpg"
            )
        )

}

class PreviewPictureListProvider : PreviewParameterProvider<List<AstronomyPicture>> {
    override val values: Sequence<List<AstronomyPicture>>
        get() = sequenceOf(
            listOf(
                AstronomyPicture(
                    id = 1,
                    title = "Recycling Cassiopeia A",
                    desc = "For billions of years massive stars in our Milky Way Galaxy have lived spectacular lives.",
                    date = LocalDate.parse("2002-07-12"),
                    url = "https://apod.nasa.gov/apod/image/0207/casA_hst.jpg"
                ),
                AstronomyPicture(
                    id = 2,
                    title = "Simulated Supergiant Star",
                    desc = "Looking for that perfect holiday gift for an astronomer ? Consider this star in a box.",
                    date = LocalDate.parse("2000-12-22"),
                    url = "https://apod.nasa.gov/apod/image/0012/supergiantsim_freytag.jpg"
                )
            )
        )
}