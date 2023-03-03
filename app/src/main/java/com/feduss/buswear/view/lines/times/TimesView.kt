package com.feduss.buswear.view.lines.times

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.foundation.lazy.items
import androidx.wear.compose.material.Text
import com.feduss.buswear.model.TimesViewModel
import com.feduss.buswear.presentation.lines.LoadingView
import com.google.android.horologist.compose.layout.ScalingLazyColumn
import com.google.android.horologist.compose.navscaffold.ExperimentalHorologistComposeLayoutApi
import com.google.android.horologist.compose.navscaffold.ScrollableScaffoldContext

@OptIn(ExperimentalHorologistComposeLayoutApi::class)
@Composable
fun TimesView(
    viewModel: TimesViewModel = viewModel(),
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

            item {
                Text(
                    text = viewModel.lineDirection,
                    textAlign = TextAlign.Center
                )
            }
            item {
                Text(
                    text = viewModel.stop.name,
                    textAlign = TextAlign.Center
                )
            }

            items(viewModel.times) { time ->
                Text(
                    text = time,
                    color = Color(("#FFFFFFFF".toColorInt()))
                )
            }
        }
    }
}
