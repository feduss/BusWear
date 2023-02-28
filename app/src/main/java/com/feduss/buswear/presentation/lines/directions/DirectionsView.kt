package com.feduss.buswear.presentation.lines.directions

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
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.Text
import com.feduss.buswear.enums.Section
import com.feduss.buswear.presentation.lines.LoadingView
import com.feduss.buswear.model.DirectionsViewModel

@Composable
fun DirectionsView(
    viewModel: DirectionsViewModel = viewModel(),
    navController: NavController
    ) {

    val showLoadingBar by viewModel.isLoading.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 8.dp, 0.dp, 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Linea ${viewModel.lineId}",
            textAlign = TextAlign.Center
        )
        Text(
            text = "Direzioni",
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.White)
        ){}
        if(showLoadingBar) {
            LoadingView()
        } else {
            viewModel.directions.forEach() { direction ->
                Card(
                    modifier = Modifier.fillMaxWidth(0.8f),
                    onClick = {
                        val args = listOf(viewModel.lineId, direction)
                        navController.navigate(Section.LineStops.withArgs(args))
                    }
                ) {
                    Text(text = direction)
                }
            }
        }
    }
}
