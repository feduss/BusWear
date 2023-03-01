package com.feduss.buswear.presentation.lines.list

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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.Text
import com.feduss.buswear.enums.Section
import com.feduss.buswear.presentation.lines.LoadingView
import com.feduss.buswear.model.LinesViewModel

@Composable
fun LinesView(
    viewModel: LinesViewModel = viewModel(),
    navController: NavController
    ) {

    val showLoadingBar by viewModel.isLoading.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 8.dp, 0.dp, 16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Linee CTM",
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
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
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
