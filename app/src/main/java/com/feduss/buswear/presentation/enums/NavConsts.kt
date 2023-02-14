package com.feduss.buswear.presentation.enums

sealed class NavConsts(val name: String) {
    object Lines: NavConsts("lines")
    object FavoritesLines: NavConsts("favoritesLines")
    object Map: NavConsts("map")
    object Info: NavConsts("info")
}
