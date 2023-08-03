package com.liviutimar.astroviewer.ui.screens.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.liviutimar.astroviewer.R
import com.liviutimar.astroviewer.domain.models.Picture
import com.liviutimar.astroviewer.ui.utils.PreviewPictureProvider

@Composable
fun PictureList(pictures: List<Picture>, onRowClick: (pictureId: Int) -> Unit) {
    if (pictures.isNotEmpty()) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 25.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(pictures) { picture ->
                PictureRow(
                    picture = picture,
                    onClick = { pictureId -> onRowClick(pictureId) }
                )
            }
        }
    }
}

@Composable
private fun PictureRow(picture: Picture, onClick: (pictureId: Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onClick(picture.id) }),
        verticalAlignment = Alignment.CenterVertically
    ) {
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
    NetworkImage(
        url = url,
        modifier = Modifier
            .size(40.dp)
            .clip(shape = CircleShape),
        crossfadeMillis = 200,
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
private fun PictureDate(date: String) {
    TextCustom(
        text = date,
        fontSize = 14.sp
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewPictureRow(@PreviewParameter(PreviewPictureProvider::class) picture: Picture) {
    PictureRow(picture = picture, onClick = {})
}