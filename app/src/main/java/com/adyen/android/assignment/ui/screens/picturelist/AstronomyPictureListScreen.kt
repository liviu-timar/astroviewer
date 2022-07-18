package com.adyen.android.assignment.ui.screens.picturelist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.adyen.android.assignment.R
import com.adyen.android.assignment.domain.models.AstronomyPicture
import com.adyen.android.assignment.domain.usecases.SortBy
import com.adyen.android.assignment.ui.navigation.PictureRoutes
import com.adyen.android.assignment.ui.theme.BackgroundSecondary
import com.adyen.android.assignment.ui.theme.Primary
import com.adyen.android.assignment.ui.utils.PreviewPictureListProvider
import com.adyen.android.assignment.ui.utils.PreviewPictureProvider
import com.adyen.android.assignment.ui.widgets.ButtonCustom
import com.adyen.android.assignment.ui.widgets.CustomTopAppBar
import com.adyen.android.assignment.ui.widgets.TextCustom
import com.adyen.android.assignment.ui.widgets.TextCustomMedium
import java.time.LocalDate

@Composable
fun AstronomyPictureListScreen(viewModel: AstronomyPictureListViewModel, navController: NavController) {
    val pictureCount = 15

    LaunchedEffect(key1 = Unit) {
        if (viewModel.isDataFirstLoad) {
            viewModel.getPictureList(count = pictureCount)
            viewModel.isDataFirstLoad = false
        }
    }

    val pictureList by viewModel.pictures.observeAsState()
    var showSortPicturesDialog by remember { mutableStateOf(false) }

    Column {
        CustomTopAppBar(
            title = stringResource(id = R.string.our_universe),
            onSortClick = { showSortPicturesDialog = true }
        )
        PictureList(
            pictureList = pictureList,
            onRowClick = { pictureId ->
                navController.navigate(
                    PictureRoutes.PictureDetails.createRouteWithArgs(pictureId = pictureId)
                )
            }
        )
    }

    if (showSortPicturesDialog) {
        SortPicturesDialog(
            onDismissClick = { showSortPicturesDialog = false },
            onConfirmClick = { sortBy ->
                viewModel.getPictureList(
                    refresh = false,
                    count = pictureCount,
                    sortBy = sortBy
                )
                showSortPicturesDialog = false
            }
        )
    }
}

@Composable
private fun PictureList(pictureList: List<AstronomyPicture>?, onRowClick: (pictureId: Int) -> Unit) {
    pictureList?.let { pictures ->
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
}

@Composable
private fun PictureRow(picture: AstronomyPicture, onClick: (pictureId: Int) -> Unit) {
    Row(
        modifier = Modifier.clickable(onClick = { onClick(picture.id) }),
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

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun SortPicturesDialog(onDismissClick: () -> Unit, onConfirmClick: (SortBy) -> Unit) {
    Dialog(
        onDismissRequest = onDismissClick,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        DialogContent(
            onDismissClick = onDismissClick,
            onConfirmClick = onConfirmClick
        )
    }
}

@Composable
private fun DialogContent(onDismissClick: () -> Unit, onConfirmClick: (SortBy) -> Unit) {
    var sortPicturesBy by remember { mutableStateOf(SortBy.DATE_DESC) }

    Column(
        modifier = Modifier
            .padding(all = 40.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(size = 10.dp))
            .background(color = BackgroundSecondary)
            .padding(all = 20.dp)
    ) {
        DialogTitle()
        Spacer(modifier = Modifier.height(20.dp))
        DialogOption(
            label = stringResource(id = R.string.sort_by_title),
            selected = sortPicturesBy == SortBy.TITLE_ASC,
            onClick = { sortPicturesBy = SortBy.TITLE_ASC }
        )
        Spacer(modifier = Modifier.height(15.dp))
        DialogOption(
            label = stringResource(id = R.string.sort_by_date),
            selected = sortPicturesBy == SortBy.DATE_DESC,
            onClick = { sortPicturesBy = SortBy.DATE_DESC }
        )
        Spacer(modifier = Modifier.height(40.dp))
        DialogButton(
            isConfirmButton = true,
            onClick = { onConfirmClick(sortPicturesBy) }
        )
        Spacer(modifier = Modifier.height(15.dp))
        DialogButton(
            isConfirmButton = false,
            onClick = onDismissClick
        )
    }
}

@Composable
private fun DialogTitle() {
    TextCustomMedium(
        text = stringResource(R.string.sort_pictures),
        fontSize = 20.sp
    )
}

@Composable
private fun DialogOption(label: String, selected: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(end = 10.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            ),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextCustom(
            text = label,
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1
        )
        RadioButton(
            selected = selected,
            modifier = Modifier
                .size(20.dp)
                .indication(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ),
            onClick = onClick,
            colors = RadioButtonDefaults.colors(
                selectedColor = Primary,
                unselectedColor = Color.White,
            )
        )
    }
}

@Composable
private fun DialogButton(isConfirmButton: Boolean, onClick: () -> Unit) {
    ButtonCustom(
        onClick = onClick,
        label = if (isConfirmButton) stringResource(id = R.string.apply) else stringResource(id = R.string.cancel),
        backgroundColor = if (isConfirmButton) Primary else BackgroundSecondary
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewPictureRow(@PreviewParameter(PreviewPictureProvider::class) picture: AstronomyPicture) {
    PictureRow(picture = picture, onClick = {})
}

@Preview(showBackground = true)
@Composable
fun PreviewPictureList(@PreviewParameter(PreviewPictureListProvider::class) pictureList: List<AstronomyPicture>) {
    PictureList(pictureList = pictureList, onRowClick = {})
}

@Preview(showBackground = true)
@Composable
fun PreviewSortDialog() {
    SortPicturesDialog(onDismissClick = {}, onConfirmClick = {})
}