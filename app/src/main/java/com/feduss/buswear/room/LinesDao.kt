package com.feduss.buswear.room

import androidx.room.Dao
import androidx.room.Query

@Dao
interface LinesDao {
    @Query("SELECT r.route_id " +
           "FROM Routes r " +
           "ORDER BY r.route_id"
    )
    fun getLines(): List<String>

    @Query("SELECT DISTINCT t.trip_headsign " +
           "FROM Trips t " +
           "WHERE t.route_id = :lineId"
    )
    fun getDirection(lineId: String): List<String>

    @Query("SELECT DISTINCT s.stop_name " +
            "FROM Stops s " +
            "WHERE s.stop_id " +
            "IN (" +
            "   SELECT st.stop_id " +
            "   FROM Stop_times st " +
            "   WHERE st.trip_id " +
            "   IN ( " +
            "       SELECT t.trip_id " +
            "       FROM Trips t " +
            "       WHERE t.route_id = :lineId" +
            "       AND t.trip_headsign LIKE :lineDirection " +
            "       )" +
            "   )"
    )
    fun getStops(lineId: String, lineDirection: String): List<String>
}
