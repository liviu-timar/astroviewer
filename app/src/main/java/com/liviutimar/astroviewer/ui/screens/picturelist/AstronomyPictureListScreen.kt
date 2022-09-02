package com.liviutimar.astroviewer.ui.screens.picturelist

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
import com.liviutimar.astroviewer.R
import com.liviutimar.astroviewer.domain.usecases.SortBy
import com.liviutimar.astroviewer.ui.navigation.PictureRoutes
import com.liviutimar.astroviewer.ui.screens.common.*
import com.liviutimar.astroviewer.ui.theme.BackgroundSecondary
import com.liviutimar.astroviewer.ui.theme.Primary
import com.liviutimar.astroviewer.ui.utils.PreviewPictureListProvider
import com.liviutimar.astroviewer.ui.utils.PreviewPictureProvider

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun AstronomyPictureListScreen(viewModel: AstronomyPictureListViewModel, navController: NavController) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var showSortPicturesDialog by rememberSaveable { mutableStateOf(false) }

    if (uiState.error != null) {
        // Could have shown cached pictures, but I'm displaying an error message for illustration purposes.
        val errorType = when {
            uiState.error!!.isNetworkError -> ErrorType.NETWORK_ERROR
            uiState.error!!.isApiError -> ErrorType.API_ERROR
            else -> ErrorType.API_ERROR // We can also use an Unknown error type
        }

        Error(
            errorType = errorType,
            onTryAgain = { viewModel.getPictureList() }
        )
    } else {
        if (uiState.isDataFirstLoad) {
            LaunchedEffect(key1 = Unit) { viewModel.getPictureList() }
        }

        Column {
            CustomTopAppBar(
                title = stringResource(id = R.string.our_universe),
                onFetchClick = {
                    viewModel.getPictureList(refresh = true)
                },
                onSortClick = { showSortPicturesDialog = true }
            )

            if (uiState.isLoadingPictures) {
                Shimmer { ShimmerPictureList(brush = this) }
            } else {
                PictureList(
                    pictures = uiState.pictures,
                    onRowClick = { pictureId ->
                        navController.navigate(
                            PictureRoutes.PictureDetails.createRouteWithArgs(pictureId = pictureId)
                        )
                    }
                )
            }
        }

        if (showSortPicturesDialog) {
            var selectedSortOption by rememberSaveable { mutableStateOf(uiState.picturesSortedBy) }

            SortPicturesDialog(
                sortBy = selectedSortOption,
                onOptionClick = { clickedOption -> selectedSortOption = clickedOption },
                onDismissClick = { showSortPicturesDialog = false },
                onConfirmClick = {
                    viewModel.getPictureList(
                        refresh = false,
                        sortBy = selectedSortOption
                    )
                    showSortPicturesDialog = false
                }
            )
        }
    }
}

@Composable
private fun PictureList(pictures: List<PictureListItemUiState>, onRowClick: (pictureId: Int) -> Unit) {
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
private fun PictureRow(picture: PictureListItemUiState, onClick: (pictureId: Int) -> Unit) {
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
    onConfirmClick: () -> Unit,
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
    onConfirmClick: () -> Unit,
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
            onClick = { onConfirmClick() }
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
private fun Error(errorType: ErrorType, onTryAgain: () -> Unit) {
    val error = when (errorType) {
        ErrorType.NETWORK_ERROR -> {
            Pair(R.string.no_network_connection, R.string.check_network_connection)
        }
        ErrorType.API_ERROR -> {
            Pair(R.string.api_error, R.string.try_again_later)
        }
    }

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
            text = stringResource(id = error.first),
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(15.dp))
        TextCustom(
            text = stringResource(id = error.second),
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

enum class ErrorType {
    NETWORK_ERROR, API_ERROR
}

@Preview(showBackground = true)
@Composable
fun PreviewPictureRow(@PreviewParameter(PreviewPictureProvider::class) picture: PictureListItemUiState) {
    PictureRow(picture = picture, onClick = {})
}

@Preview(showBackground = true)
@Composable
fun PreviewPictureList(@PreviewParameter(PreviewPictureListProvider::class) pictureList: List<PictureListItemUiState>) {
    PictureList(pictures = pictureList, onRowClick = {})
}

@Preview(showBackground = true)
@Composable
fun PreviewSortDialog() {
    SortPicturesDialog(sortBy = SortBy.DATE_DESC, onOptionClick = {}, onDismissClick = {}, onConfirmClick = {})
}