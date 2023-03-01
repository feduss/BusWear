package com.feduss.buswear.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Routes(
    @PrimaryKey @ColumnInfo(name = "route_id") val routeId: String,
    @ColumnInfo(name = "agency_id") val agencyId: String?,
    @ColumnInfo(name = "route_short_name") val routeShortName: String?,
    @ColumnInfo(name = "route_long_name") val routeLongName: String?,
    @ColumnInfo(name = "route_desc") val routeDesc: String?,
    @ColumnInfo(name = "route_type") val routeType: String?,
    @ColumnInfo(name = "route_url") val routeUrl: String?,
    @ColumnInfo(name = "route_color") val routeColor: String?,
    @ColumnInfo(name = "route_text_color") val routeTextColor: String?
)

@Entity
data class Trips(
    @ColumnInfo(name = "route_id") val routeId: String?,
    @ColumnInfo(name = "service_id") val serviceId: String?,
    @PrimaryKey @ColumnInfo(name = "trip_id") val tripId: String,
    @ColumnInfo(name = "trip_headsign") val tripHeadSign: String?,
    @ColumnInfo(name = "trip_short_name") val tripShortName: String?,
    @ColumnInfo(name = "direction_id") val directionId: String?,
    @ColumnInfo(name = "block_id") val blockId: String?,
    @ColumnInfo(name = "shape_id") val shapeId: String?,
    @ColumnInfo(name = "wheelchair_accessible") val wheelChairAccessible: String?,
    @ColumnInfo(name = "bikes_allowed") val bikesAllowed: String?
)

@Entity
data class Stops(
    @PrimaryKey @ColumnInfo(name = "stop_id") val stopId: String,
    @ColumnInfo(name = "stop_code") val stopCode: String?,
    @ColumnInfo(name = "stop_name") val stopName: String?,
    @ColumnInfo(name = "stop_desc") val stopDesc: String?,
    @ColumnInfo(name = "stop_lat") val stopLat: String?,
    @ColumnInfo(name = "stop_lon") val stopLon: String?,
    @ColumnInfo(name = "zone_id") val zoneId: String?,
    @ColumnInfo(name = "stop_url") val stopUrl: String?,
    @ColumnInfo(name = "location_type") val locationType: String?,
    @ColumnInfo(name = "parent_station") val parentStation: String?,
    @ColumnInfo(name = "stop_timezone") val stopTimezone: String?,
    @ColumnInfo(name = "wheelchair_boarding") val wheelChairBoarding: String?
)

@Entity
data class Stop_Times(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "trip_id") val tripId: String?,
    @ColumnInfo(name = "arrival_time") val arrivalTime: String?,
    @ColumnInfo(name = "departure_time") val departureTime: String?,
    @ColumnInfo(name = "stop_id") val stopId: String?,
    @ColumnInfo(name = "stop_sequence") val stop_sequence: String?,
    @ColumnInfo(name = "stop_headsign") val stopHeadsign: String?,
    @ColumnInfo(name = "pickup_type") val pickupType: String?,
    @ColumnInfo(name = "drop_off_type") val dropOffType: String?,
    @ColumnInfo(name = "shape_dist_traveled") val shapeDistTraveled: String?,
    @ColumnInfo(name = "timepoint") val timepoint: String?
)

@Entity
data class Shapes(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "shape_id") val shapeId: String?,
    @ColumnInfo(name = "shape_pt_lat") val shapePtLat: String?,
    @ColumnInfo(name = "shape_pt_lon") val shapePtLon: String?,
    @ColumnInfo(name = "shape_pt_sequence") val shapePtSequence: String?,
    @ColumnInfo(name = "shape_dist_traveled") val shapeDistTraveled: String?
)

@Entity(tableName = "Fare_attributes")
data class FareAttributes(
    @PrimaryKey @ColumnInfo(name = "fare_id") val fareId: String,
    @ColumnInfo(name = "price") val price: String?,
    @ColumnInfo(name = "currency_type") val currencyType: String?,
    @ColumnInfo(name = "payment_method") val paymentMethod: String?,
    @ColumnInfo(name = "transfers") val transfers: String?,
    @ColumnInfo(name = "agency_id") val agencyId: String?,
    @ColumnInfo(name = "transfer_duration") val transferDuration: String?
)

@Entity
data class Calendar_Dates(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "service_id") val serviceId: String?,
    @ColumnInfo(name = "date") val date: String?,
    @ColumnInfo(name = "exception_type") val exceptionType: String?
)

@Entity
data class Agency(
    @PrimaryKey @ColumnInfo(name = "agency_id") val agencyId: String,
    @ColumnInfo(name = "agency_name") val agencyName: String?,
    @ColumnInfo(name = "agency_url") val agencyUrl: String?,
    @ColumnInfo(name = "agency_timezone") val agencyTimezone: String?,
    @ColumnInfo(name = "agency_lang") val agencyLang: String?,
    @ColumnInfo(name = "agency_phone") val agencyPhone: String?,
    @ColumnInfo(name = "agency_fare_url") val agencyFareUrl: String?,
    @ColumnInfo(name = "agency_email") val agencyEmail: String?
)

