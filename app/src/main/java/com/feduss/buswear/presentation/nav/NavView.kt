package com.feduss.buswear.presentation.nav

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.feduss.buswear.presentation.enums.Params
import com.feduss.buswear.presentation.enums.Section
import com.feduss.buswear.presentation.favorites.FavoritesLinesView
import com.feduss.buswear.presentation.info.InfoView
import com.feduss.buswear.presentation.lines.directions.DirectionsView
import com.feduss.buswear.presentation.lines.directions.DirectionsViewModel
import com.feduss.buswear.presentation.lines.directions.DirectionsViewModelFactory
import com.feduss.buswear.presentation.lines.list.LinesView
import com.feduss.buswear.presentation.map.MapView
import com.google.android.horologist.compose.pager.PagerScreen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NavView(
    context: Context,
    viewModel: NavViewModel = viewModel(),
    startDestination: String = Section.Home.baseRoute
) {

    MaterialTheme {
        val navController = rememberSwipeDismissableNavController()

        SwipeDismissableNavHost(
            modifier = Modifier.background(Color.Black),
            navController = navController,
            startDestination = startDestination
        ){
            composable(route = Section.Home.baseRoute) {
                PagerScreen(count = viewModel.numberOfPages) { selectedPage ->
                    when(selectedPage) {
                        0 -> LinesView(
                            db = viewModel.getReadableDb(context),
                            navController = navController
                        )
                        1 -> FavoritesLinesView()
                        2 -> MapView()
                        3 -> InfoView()
                    }
                }
            }

            composable(route = Section.LineDirections.parametricRoute, arguments = listOf(
                navArgument(Params.LineId.name) { type = NavType.StringType }
            )
            ) { navBackStackEntry ->
                val lineId: String? = navBackStackEntry.arguments?.getString(Params.LineId.name)
                lineId?.let { lineIdNotNull ->
                    val directionsViewModel: DirectionsViewModel = viewModel(factory = DirectionsViewModelFactory(lineIdNotNull))
                    DirectionsView(
                        db = viewModel.getReadableDb(context),
                        viewModel = directionsViewModel
                    )
                }
            }
        }
    }
}