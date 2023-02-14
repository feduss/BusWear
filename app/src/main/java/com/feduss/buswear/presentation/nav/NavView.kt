package com.feduss.buswear.presentation.nav

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.PageIndicatorState
import com.feduss.buswear.presentation.favorites.FavoritesLinesView
import com.feduss.buswear.presentation.info.InfoView
import com.feduss.buswear.presentation.lines.LinesView
import com.feduss.buswear.presentation.map.MapView
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.android.horologist.compose.pager.PagerScreen

@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
@Composable
@Preview
fun NavView(viewModel: NavViewModel = viewModel()) {

    MaterialTheme {
        PagerScreen(count = viewModel.numberOfPages) { selectedPage ->
            when(selectedPage) {
                0 -> LinesView()
                1 -> FavoritesLinesView()
                2 -> MapView()
                3 -> InfoView()
            }
        }
    }
}