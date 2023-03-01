package com.feduss.buswear.room

import androidx.room.Dao
import androidx.room.Query
import com.feduss.buswear.model.StopModel

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

    @Query("SELECT DISTINCT s.stop_id as id, s.stop_name as name " +
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
    fun getStops(lineId: String, lineDirection: String): List<StopModel>

    //TODAY times
    @Query("SELECT st.arrival_time " +
            "FROM Stop_times st " +
            "WHERE st.stop_id = :stopId " +
            "AND time(st.arrival_time) >= time('now') " +
            "AND st.trip_id IN ( " +
            "   SELECT DISTINCT t.trip_id " +
            "   FROM Trips t " +
            "   WHERE t.route_id = :lineId " +
            "   AND t.service_id IN ( " +
            "       SELECT cd.service_id " +
            "       FROM calendar_dates cd " +
            "       WHERE substr(cd.date, 1, 4) || '-' || " +
            "             substr(cd.date, 5, 2) || '-' || substr(cd.date, 7, 2) = " +
            "             strftime('%Y-%m-%d', 'now')" +
            "   )" +
            ")" +
            "ORDER BY st.arrival_time"
    )
    fun getScheduledTimes(lineId: String, stopId: String): List<String>
}
