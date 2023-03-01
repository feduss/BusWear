package com.feduss.buswear.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Routes::class, Trips::class, Stops::class, Stop_Times::class, Shapes::class,
    FareAttributes::class, Calendar_Dates::class, Agency::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun linesDao(): LinesDao
}
