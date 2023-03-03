package com.feduss.buswear.view.lines.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import com.feduss.buswear.model.LinesViewModel
import com.google.android.horologist.compose.navscaffold.ExperimentalHorologistComposeLayoutApi
import com.google.android.horologist.compose.navscaffold.ScrollableScaffoldContext
import com.google.android.horologist.compose.layout.ScalingLazyColumn

@OptIn(ExperimentalHorologistComposeLayoutApi::class)
@Composable
fun LinesView(
    viewModel: LinesViewModel = viewModel(),
    navController: NavController,
    scrollableContext: ScrollableScaffoldContext
) {

    val showLoadingBar by viewModel.isLoading.collectAsState()

    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

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
                        text = "Linee CTM",
                        textAlign = TextAlign.Center
                    )
                }
                items(viewModel.lines) { line ->
                    Card(
                        modifier = Modifier.fillMaxWidth(0.8f),
                        onClick = {
                            val args = listOf(line)
                            navController.navigate(Section.LineDirections.withArgs(args))
                        }
                    ) {
                        Text(text = "Linea $line")
                    }
                }
            }
        }

    }
}
