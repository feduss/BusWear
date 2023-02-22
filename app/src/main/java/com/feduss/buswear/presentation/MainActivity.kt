package com.feduss.buswear.presentation

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.feduss.buswear.presentation.nav.NavView
import com.feduss.buswear.presentation.utils.DbHelper

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NavView(context = this)
        }
    }
}

