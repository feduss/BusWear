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
import com.feduss.buswear.enums.Params
import com.feduss.buswear.enums.Section
import com.feduss.buswear.view.favorites.FavoritesLinesView
import com.feduss.buswear.presentation.info.InfoView
import com.feduss.buswear.presentation.lines.directions.DirectionsView
import com.feduss.buswear.model.DirectionsViewModel
import com.feduss.buswear.factory.DirectionsViewModelFactory
import com.feduss.buswear.presentation.lines.list.LinesView
import com.feduss.buswear.factory.LinesViewModelFactory
import com.feduss.buswear.view.lines.stops.StopsView
import com.feduss.buswear.model.StopsViewModel
import com.feduss.buswear.factory.StopsViewModelFactory
import com.feduss.buswear.factory.TimesViewModelFactory
import com.feduss.buswear.presentation.map.MapView
import com.feduss.buswear.model.NavViewModel
import com.feduss.buswear.model.TimesViewModel
import com.feduss.buswear.room.AppDatabase
import com.feduss.buswear.view.lines.times.TimesView
import com.google.android.horologist.compose.pager.PagerScreen
import java.net.URLDecoder

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NavView(
    context: Context,
    db: AppDatabase,
    viewModel: NavViewModel = viewModel(),
    startDestination: String = Section.LinesList.baseRoute
) {

    MaterialTheme {
        val navController = rememberSwipeDismissableNavController()

        SwipeDismissableNavHost(
            modifier = Modifier.background(Color.Black),
            navController = navController,
            startDestination = startDestination
        ){
            composable(route = Section.LinesList.baseRoute) {
                PagerScreen(count = viewModel.numberOfPages) { selectedPage ->
                    when(selectedPage) {
                        0 -> LinesView(
                            viewModel = viewModel(
                                factory = LinesViewModelFactory(db = db)
                            ),
                            navController = navController
                        )
                        1 -> FavoritesLinesView()
                        2 -> MapView()
                        3 -> InfoView()
                    }
                }
            }

            composable(
                route = Section.LineDirections.parametricRoute,
                arguments = listOf(
                    navArgument(Params.LineId.name) { type = NavType.StringType }
                )
            ) { navBackStackEntry ->
                val lineId: String = navBackStackEntry.arguments?.getString(Params.LineId.name) ?: ""
                val directionsViewModel: DirectionsViewModel = viewModel(
                    factory = DirectionsViewModelFactory(
                        lineId = lineId,
                        db = db
                    )
                )
                DirectionsView(
                    viewModel = directionsViewModel,
                    navController = navController
                )
            }

            composable(
                route = Section.LineStops.parametricRoute,
                arguments = listOf(
                    navArgument(Params.LineId.name) { type = NavType.StringType },
                    navArgument(Params.LineDirection.name) { type = NavType.StringType }
                )
            ) { navBackStackEntry ->
                val lineId: String = navBackStackEntry.arguments?.getString(Params.LineId.name) ?: ""
                val lineDirection: String = navBackStackEntry.arguments?.getString(Params.LineDirection.name) ?: ""
                val decodedDirection: String = URLDecoder.decode(lineDirection, Charsets.UTF_8.name()).toString()
                val stopsViewModel: StopsViewModel = viewModel(
                    factory = StopsViewModelFactory(
                        lineId = lineId,
                        lineDirection = decodedDirection,
                        db = db
                    )
                )
                StopsView(
                    viewModel = stopsViewModel,
                    navController = navController
                )
            }

            composable(
                route = Section.LineTimes.parametricRoute,
                arguments = listOf(
                    navArgument(Params.LineId.name) { type = NavType.StringType },
                    navArgument(Params.LineDirection.name) { type = NavType.StringType },
                    navArgument(Params.StopId.name) { type = NavType.StringType }
                )
            ) { navBackStackEntry ->
                val lineId: String = navBackStackEntry.arguments?.getString(Params.LineId.name) ?: ""
                val stopId: String = navBackStackEntry.arguments?.getString(Params.StopId.name) ?: ""
                val timesViewModel: TimesViewModel = viewModel(
                    factory = TimesViewModelFactory(
                        lineId = lineId,
                        stopId = stopId,
                        db = db
                    )
                )
                TimesView(
                    viewModel = timesViewModel
                )
            }
        }
    }
}