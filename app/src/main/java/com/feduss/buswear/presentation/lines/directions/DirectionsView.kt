package com.feduss.buswear.presentation.lines.directions

import android.database.sqlite.SQLiteDatabase
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.*

@Composable
fun DirectionsView(
    viewModel: DirectionsViewModel = viewModel(),
    db: SQLiteDatabase
    ) {

    viewModel.setDirections(db)

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
        viewModel.directions.forEach() { direction ->
            Card(
                modifier = Modifier.fillMaxWidth(0.8f),
                onClick = { }
            ) {
                Text(text = direction)
            }
        }

    }
}
