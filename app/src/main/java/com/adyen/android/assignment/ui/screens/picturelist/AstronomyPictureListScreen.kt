package com.adyen.android.assignment.ui.screens.picturelist

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.adyen.android.assignment.R
import com.adyen.android.assignment.domain.usecases.SortBy
import com.adyen.android.assignment.ui.navigation.PictureRoutes
import com.adyen.android.assignment.ui.screens.common.*
import com.adyen.android.assignment.ui.screens.picturelist.models.PictureListItem
import com.adyen.android.assignment.ui.theme.BackgroundSecondary
import com.adyen.android.assignment.ui.theme.Primary
import com.adyen.android.assignment.ui.utils.PreviewPictureListProvider
import com.adyen.android.assignment.ui.utils.PreviewPictureProvider
import com.adyen.android.assignment.ui.utils.hasNetworkConnection

private const val PICTURE_COUNT = 15

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun AstronomyPictureListScreen(viewModel: AstronomyPictureListViewModel, navController: NavController) {
    val context = LocalContext.current
    var hasNetworkConnection by rememberSaveable { mutableStateOf(context.hasNetworkConnection()) }
    val pictureList by viewModel.pictures.collectAsStateWithLifecycle()

    if (viewModel.isDataFirstLoad) {
        if (hasNetworkConnection) {
            LaunchedEffect(key1 = Unit) {
                viewModel.getPictureList(count = PICTURE_COUNT)
                viewModel.isDataFirstLoad = false
            }

            ScreenContent(
                viewModel = viewModel,
                navController = navController,
                pictureList = pictureList,
            )
        } else {
            // Could have shown cached pictures, but I'm displaying an error message for illustration purposes.
            NoNetworkConnection(onTryAgain = { hasNetworkConnection = context.hasNetworkConnection() })
        }
    } else {
        ScreenContent(
            viewModel = viewModel,
            navController = navController,
            pictureList = pictureList,
        )
    }
}

@Composable
private fun ScreenContent(
    viewModel: AstronomyPictureListViewModel,
    navController: NavController,
    pictureList: List<PictureListItem>?
) {
    var showSortPicturesDialog by rememberSaveable { mutableStateOf(false) }

    Column {
        CustomTopAppBar(
            title = stringResource(id = R.string.our_universe),
            onFetchClick = {
                viewModel.clearPictureList()
                viewModel.getPictureList(
                    refresh = true,
                    count = PICTURE_COUNT,
                    sortBy = viewModel.sortPicturesBy
                )
            },
            onSortClick = { showSortPicturesDialog = true }
        )

        pictureList?.let { pictures ->
            PictureList(
                pictures = pictures,
                onRowClick = { pictureId ->
                    navController.navigate(
                        PictureRoutes.PictureDetails.createRouteWithArgs(pictureId = pictureId)
                    )
                }
            )
        } ?: Shimmer { ShimmerPictureList(brush = this) }
    }

    if (showSortPicturesDialog) {
        var selectedSortOption by rememberSaveable { mutableStateOf(viewModel.sortPicturesBy) }

        SortPicturesDialog(
            sortBy = selectedSortOption,
            onOptionClick = { clickedOption -> selectedSortOption = clickedOption },
            onDismissClick = {
                selectedSortOption = viewModel.sortPicturesBy
                showSortPicturesDialog = false
            },
            onConfirmClick = { sortBy ->
                viewModel.getPictureList(
                    refresh = false,
                    count = PICTURE_COUNT,
                    sortBy = sortBy
                )

                viewModel.sortPicturesBy = selectedSortOption
                showSortPicturesDialog = false
            }
        )
    }
}

@Composable
private fun PictureList(pictures: List<PictureListItem>, onRowClick: (pictureId: Int) -> Unit) {
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
private fun PictureRow(picture: PictureListItem, onClick: (pictureId: Int) -> Unit) {
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
    AstronomyImage(
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

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun SortPicturesDialog(
    sortBy: SortBy,
    onOptionClick: (SortBy) -> Unit,
    onDismissClick: () -> Unit,
    onConfirmClick: (SortBy) -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissClick,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        DialogContent(
            sortBy = sortBy,
            onOptionClick = onOptionClick,
            onDismissClick = onDismissClick,
            onConfirmClick = onConfirmClick
        )
    }
}

@Composable
private fun DialogContent(
    sortBy: SortBy,
    onOptionClick: (SortBy) -> Unit,
    onDismissClick: () -> Unit,
    onConfirmClick: (SortBy) -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(all = 40.dp)
            .fillMaxWidth() // Should have smaller width on landscape (including buttons)
            .clip(shape = RoundedCornerShape(size = 10.dp))
            .background(color = BackgroundSecondary)
            .padding(all = 20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        DialogTitle()
        Spacer(modifier = Modifier.height(20.dp))
        DialogOption(
            label = stringResource(id = R.string.sort_by_title),
            selected = sortBy == SortBy.TITLE_ASC,
            onClick = { onOptionClick(SortBy.TITLE_ASC) }
        )
        Spacer(modifier = Modifier.height(20.dp))
        DialogOption(
            label = stringResource(id = R.string.sort_by_date),
            selected = sortBy == SortBy.DATE_DESC,
            onClick = { onOptionClick(SortBy.DATE_DESC) }
        )
        Spacer(modifier = Modifier.height(40.dp))
        DialogButton(
            isConfirmButton = true,
            onClick = { onConfirmClick(sortBy) }
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

@Composable
private fun NoNetworkConnection(onTryAgain: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(horizontal = 35.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_vector),
            contentDescription = null,
            modifier = Modifier.size(120.dp)
        )
        Spacer(modifier = Modifier.height(40.dp))
        TextCustomMedium(
            text = stringResource(id = R.string.no_network_connection),
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(15.dp))
        TextCustom(
            text = stringResource(id = R.string.check_network_connection),
            textAlign = TextAlign.Center,
            lineHeight = 25.sp
        )
        Spacer(modifier = Modifier.height(30.dp))
        ButtonCustom(
            onClick = onTryAgain,
            label = stringResource(id = R.string.try_again)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPictureRow(@PreviewParameter(PreviewPictureProvider::class) picture: PictureListItem) {
    PictureRow(picture = picture, onClick = {})
}

@Preview(showBackground = true)
@Composable
fun PreviewPictureList(@PreviewParameter(PreviewPictureListProvider::class) pictureList: List<PictureListItem>) {
    PictureList(pictures = pictureList, onRowClick = {})
}

@Preview(showBackground = true)
@Composable
fun PreviewSortDialog() {
    SortPicturesDialog(sortBy = SortBy.DATE_DESC, onOptionClick = {}, onDismissClick = {}, onConfirmClick = {})
}