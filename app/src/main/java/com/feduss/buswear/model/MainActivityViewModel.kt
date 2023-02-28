package com.feduss.buswear.model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.feduss.buswear.room.AppDatabase

class MainActivityViewModel: ViewModel() {

    private val dbName: String = "buswear.db"

    fun getDb(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, dbName)
            .createFromAsset("database/$dbName")
            .build()
    }
}