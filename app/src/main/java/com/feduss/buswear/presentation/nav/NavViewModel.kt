package com.feduss.buswear.presentation.nav

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.lifecycle.ViewModel
import com.feduss.buswear.presentation.utils.DbHelper

class NavViewModel: ViewModel() {

    val numberOfPages = 4
    private val dbName = "buswear.db"

    fun getReadableDb(context: Context): SQLiteDatabase {
        return DbHelper(context, dbName, null, 1).readableDatabase
    }
}