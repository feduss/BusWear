package com.feduss.buswear.presentation.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.io.FileOutputStream

class DbHelper(
    private val context: Context,
    private val dbName: String,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, dbName, factory, version) {

    init {
        val dbPath = context.getDatabasePath(dbName)
        //val dbDir = dbPath.parent

        if (dbPath?.exists() == false) {
            //dbPath.mkdirs()
            dbPath.createNewFile()
        }

        context.assets?.open(dbName)?.copyTo(
            out = FileOutputStream(dbPath),
            bufferSize = 8 * 1024
        )
    }

    override fun onCreate(db: SQLiteDatabase?) {}

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}
}