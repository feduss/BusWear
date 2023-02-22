package com.feduss.buswear.presentation.lines.list

import android.database.sqlite.SQLiteDatabase
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.wear.compose.material.*
import com.feduss.buswear.presentation.enums.Section

@Composable
fun LinesView(
    viewModel: LinesViewModel = viewModel(),
    db: SQLiteDatabase,
    navController: NavController
    ) {

    viewModel.setLines(db)

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
