package com.feduss.buswear.view.lines.directions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.wear.compose.foundation.lazy.items
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.Text
import com.feduss.buswear.enums.Section
import com.feduss.buswear.presentation.lines.LoadingView
import com.feduss.buswear.model.DirectionsViewModel
import com.google.android.horologist.compose.layout.ScalingLazyColumn
import com.google.android.horologist.compose.navscaffold.ExperimentalHorologistComposeLayoutApi
import com.google.android.horologist.compose.navscaffold.ScrollableScaffoldContext
import java.net.URLEncoder

@OptIn(ExperimentalHorologistComposeLayoutApi::class)
@Composable
fun DirectionsView(
    viewModel: DirectionsViewModel = viewModel(),
    navController: NavController,
    scrollableContext: ScrollableScaffoldContext
    ) {

    val showLoadingBar by viewModel.isLoading.collectAsState()

    ScalingLazyColumn(
        modifier = Modifier.fillMaxSize(),
        columnState = scrollableContext.columnState,
    ) {
        if(showLoadingBar) {
            item {
                LoadingView()
            }
        } else {
            item {
                Text(
                    text = "Linea ${viewModel.lineId}",
                    textAlign = TextAlign.Center
                )
            }

            items(viewModel.directions) { direction ->
                Card(
                    modifier = Modifier.fillMaxWidth(0.8f),
                    onClick = {
                        //The direction is encoded because it can contain spaces
                        val encodedDirection =
                            URLEncoder.encode(direction, Charsets.UTF_8.name()).toString()
                        val args = listOf(viewModel.lineId, encodedDirection)
                        navController.navigate(Section.LineStops.withArgs(args))
                    }
                ) {
                    Text(text = direction)
                }
            }
        }
    }
}
