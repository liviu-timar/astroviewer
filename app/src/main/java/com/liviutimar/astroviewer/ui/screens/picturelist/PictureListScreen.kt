package com.liviutimar.astroviewer.ui.screens.picturelist

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.ripple.LocalRippleTheme
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
import com.liviutimar.astroviewer.domain.models.Picture
import com.liviutimar.astroviewer.domain.usecases.SortBy
import com.liviutimar.astroviewer.ui.navigation.PictureRoutes
import com.liviutimar.astroviewer.ui.screens.common.*
import com.liviutimar.astroviewer.ui.theme.BackgroundSecondary
import com.liviutimar.astroviewer.ui.theme.NoRippleTheme
import com.liviutimar.astroviewer.ui.theme.Primary
import com.liviutimar.astroviewer.ui.utils.PreviewPictureListProvider

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun PictureListScreen(viewModel: PictureListViewModel, navController: NavController) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var showSortPicturesDialog by rememberSaveable { mutableStateOf(false) }

    Column {
        if (uiState.error == null) {
            CustomTopAppBar(
                title = stringResource(id = R.string.our_universe),
                onFetchClick = { viewModel.getPictureList(refresh = true) },
                onSortClick = { showSortPicturesDialog = true }
            )

            if (uiState.isLoading) {
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

            if (showSortPicturesDialog) {
                var selectedSortOption by remember { mutableStateOf(uiState.sortBy) }

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
        } else {
            CustomTopAppBar(title = stringResource(id = R.string.our_universe))

            // Could have shown cached pictures, but I'm displaying an error message for illustration purposes.
            val message = when {
                uiState.error!!.isNetworkError -> Pair(R.string.no_network_connection, R.string.check_network_connection)
                uiState.error!!.isApiError -> Pair(R.string.api_error, R.string.try_again_later)
                else -> Pair(R.string.api_error, R.string.try_again_later)
            }

            FullscreenMessage(
                icon = R.drawable.ic_error,
                firstLine = message.first,
                secondLine = message.second,
                onTryAgain = { viewModel.getPictureList(refresh = true) }
            )
        }
    }
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
        // Use CompositionLocal to disable the ripple effect for the RadioButton
        CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
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
fun PreviewPictureList(@PreviewParameter(PreviewPictureListProvider::class) pictureList: List<Picture>) {
    PictureList(pictures = pictureList, onRowClick = {})
}

@Preview(showBackground = true)
@Composable
fun PreviewSortDialog() {
    SortPicturesDialog(sortBy = SortBy.DATE_DESC, onOptionClick = {}, onDismissClick = {}, onConfirmClick = {})
}