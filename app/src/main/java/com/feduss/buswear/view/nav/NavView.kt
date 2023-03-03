package com.feduss.buswear.view.nav

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
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.feduss.buswear.enums.Params
import com.feduss.buswear.enums.Section
import com.feduss.buswear.view.favorites.FavoritesLinesView
import com.feduss.buswear.presentation.info.InfoView
import com.feduss.buswear.view.lines.directions.DirectionsView
import com.feduss.buswear.factory.DirectionsViewModelFactory
import com.feduss.buswear.view.lines.list.LinesView
import com.feduss.buswear.factory.LinesViewModelFactory
import com.feduss.buswear.view.lines.stops.StopsView
import com.feduss.buswear.factory.StopsViewModelFactory
import com.feduss.buswear.factory.TimesViewModelFactory
import com.feduss.buswear.model.*
import com.feduss.buswear.presentation.map.MapView
import com.feduss.buswear.room.AppDatabase
import com.feduss.buswear.view.lines.times.TimesView
import com.google.android.horologist.compose.navscaffold.ExperimentalHorologistComposeLayoutApi
import com.google.android.horologist.compose.navscaffold.WearNavScaffold
import com.google.android.horologist.compose.navscaffold.scrollable
import com.google.android.horologist.compose.pager.PagerScreen
import com.google.gson.Gson
import java.net.URLDecoder

@OptIn(ExperimentalFoundationApi::class, ExperimentalHorologistComposeLayoutApi::class)
@Composable
fun NavView(
    context: Context,
    db: AppDatabase,
    viewModel: NavViewModel = viewModel(),
    startDestination: String = Section.LinesList.baseRoute
) {

    MaterialTheme {
        val navController = rememberSwipeDismissableNavController()
        WearNavScaffold(
            modifier = Modifier.background(Color.Black),
            startDestination = startDestination,
            navController = navController
        ) {
            scrollable(route = Section.LinesList.baseRoute) { navBackStackEntry ->
                PagerScreen(count = viewModel.numberOfPages) { selectedPage ->
                    when(selectedPage) {
                        0 -> LinesView(
                            viewModel = viewModel(
                                factory = LinesViewModelFactory(db = db)
                            ),
                            navController = navController,
                            scrollableContext = navBackStackEntry
                        )
                        1 -> FavoritesLinesView()
                        2 -> MapView()
                        3 -> InfoView()
                    }
                }
            }

            scrollable(
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
                    navController = navController,
                    scrollableContext = navBackStackEntry
                )
            }

            scrollable(
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
                    navController = navController,
                    scrollableContext = navBackStackEntry
                )
            }

            scrollable(
                route = Section.LineTimes.parametricRoute,
                arguments = listOf(
                    navArgument(Params.LineId.name) { type = NavType.StringType },
                    navArgument(Params.LineDirection.name) { type = NavType.StringType },
                    navArgument(Params.Stop.name) { type = NavType.StringType }
                )
            ) { navBackStackEntry ->
                val lineId: String = navBackStackEntry.arguments?.getString(Params.LineId.name) ?: ""
                val lineDirection: String = navBackStackEntry.arguments?.getString(Params.LineDirection.name) ?: ""
                val decodedDirection: String = URLDecoder.decode(lineDirection, Charsets.UTF_8.name()).toString()
                val stop: String = navBackStackEntry.arguments?.getString(Params.Stop.name) ?: ""
                val decodedStop: StopModel = Gson().fromJson(stop, StopModel::class.java)
                val timesViewModel: TimesViewModel = viewModel(
                    factory = TimesViewModelFactory(
                        lineId = lineId,
                        lineDirection = decodedDirection,
                        stop = decodedStop,
                        db = db
                    )
                )
                TimesView(
                    viewModel = timesViewModel,
                    scrollableContext = navBackStackEntry
                )
            }
        }
    }
}