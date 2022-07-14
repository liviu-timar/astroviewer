package com.adyen.android.assignment.ui.picturelist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.adyen.android.assignment.R
import com.adyen.android.assignment.domain.models.AstronomyPicture
import com.adyen.android.assignment.ui.utils.PreviewPictureListProvider
import com.adyen.android.assignment.ui.utils.PreviewPictureProvider
import com.adyen.android.assignment.ui.widgets.CustomTopAppBar
import com.adyen.android.assignment.ui.widgets.TextCustom
import com.adyen.android.assignment.ui.widgets.TextCustomMedium
import java.time.LocalDate

@Composable
fun AstronomyPictureListScreen(viewModel: AstronomyPictureListViewModel) {
    LaunchedEffect(key1 = Unit) { viewModel.getPictureList(count = 15) }

    val pictureList by viewModel.pictures.observeAsState()

    Column {
        CustomTopAppBar(title = stringResource(id = R.string.our_universe))
        PictureList(pictureList = pictureList)
    }
}

@Composable
private fun PictureList(pictureList: List<AstronomyPicture>?) {
    pictureList?.let { pictures ->
        if (pictures.isNotEmpty()) {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 25.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                items(pictures) { picture -> PictureRow(picture = picture) }
            }
        }
    }
}

@Composable
private fun PictureRow(picture: AstronomyPicture) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        PictureImage(url = picture.url)
        Spacer(modifier = Modifier.width(15.dp))
        Column {
            PictureTitle(title = picture.title)
            Spacer(modifier = Modifier.height(7.dp))
            PictureDate(date = picture.date)
        }
    }
}

@Composable
private fun PictureImage(url: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(durationMillis = 200)
            .build(),
        contentDescription = stringResource(R.string.astronomy_picture),
        modifier = Modifier
            .size(40.dp)
            .clip(shape = CircleShape),
        contentScale = ContentScale.Crop,
        alpha = 0.8f,
        error = painterResource(id = R.drawable.ic_warped),
        fallback = painterResource(id = R.drawable.ic_warped),
        placeholder = painterResource(id = R.drawable.ic_dust)
    )
}

@Composable
private fun PictureTitle(title: String) {
    TextCustomMedium(
        text = title,
        maxLines = 1
    )
}

@Composable
private fun PictureDate(date: LocalDate) {
    TextCustom(
        text = date.toString(),
        fontSize = 14.sp
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewPictureRow(@PreviewParameter(PreviewPictureProvider::class) picture: AstronomyPicture) {
    PictureRow(picture = picture)
}

@Preview(showBackground = true)
@Composable
fun PreviewPictureList(@PreviewParameter(PreviewPictureListProvider::class) pictureList: List<AstronomyPicture>) {
    PictureList(pictureList = pictureList)
}