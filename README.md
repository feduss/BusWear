# BusWear
A wear os app to check the scheduled times of Cagliari's bus system, powered by Jetpack Compose

## GTFS to sqlite db

- Save this script as load-gtfs.sql

```
DROP TABLE IF EXISTS "agency";
CREATE TABLE "agency" (
	"agency_id"	TEXT NOT NULL,
	"agency_name"	TEXT,
	"agency_url"	TEXT,
	"agency_timezone"	TEXT,
	"agency_lang"	TEXT,
	"agency_phone"	TEXT,
	"agency_fare_url"	TEXT,
	"agency_email"	TEXT,
	PRIMARY KEY("agency_id")
);

DROP TABLE IF EXISTS "calendar_dates";
CREATE TABLE "calendar_dates" (
	"service_id"	TEXT,
	"date"	TEXT,
	"exception_type"	TEXT,
	"id"	INTEGER NOT NULL,
	PRIMARY KEY("id")
);

DROP TABLE IF EXISTS "fare_attributes";
CREATE TABLE "fare_attributes" (
	"fare_id"	TEXT NOT NULL,
	"price"	TEXT,
	"currency_type"	TEXT,
	"payment_method"	TEXT,
	"transfers"	TEXT,
	"agency_id"	TEXT,
	"transfer_duration"	TEXT,
	PRIMARY KEY("fare_id")
);

DROP TABLE IF EXISTS "routes";
CREATE TABLE "routes" (
	"route_id"	TEXT NOT NULL,
	"agency_id"	TEXT,
	"route_short_name"	TEXT,
	"route_long_name"	TEXT,
	"route_desc"	TEXT,
	"route_type"	TEXT,
	"route_url"	TEXT,
	"route_color"	TEXT,
	"route_text_color"	TEXT,
	PRIMARY KEY("route_id")
);

DROP TABLE IF EXISTS "shapes";
CREATE TABLE "shapes" (
	"shape_id"	TEXT,
	"shape_pt_lat"	TEXT,
	"shape_pt_lon"	TEXT,
	"shape_pt_sequence"	TEXT,
	"shape_dist_traveled"	TEXT,
	"id"	INTEGER NOT NULL,
	PRIMARY KEY("id")
);

DROP TABLE IF EXISTS "stop_times";
CREATE TABLE "stop_times" (
	"trip_id"	TEXT,
	"arrival_time"	TEXT,
	"departure_time"	TEXT,
	"stop_id"	TEXT,
	"stop_sequence"	TEXT,
	"stop_headsign"	TEXT,
	"pickup_type"	TEXT,
	"drop_off_type"	TEXT,
	"shape_dist_traveled"	TEXT,
	"timepoint"	TEXT,
	"id"	INTEGER NOT NULL,
	PRIMARY KEY("id")
);

DROP TABLE IF EXISTS "stops";
CREATE TABLE "stops" (
	"stop_id"	TEXT NOT NULL,
	"stop_code"	TEXT,
	"stop_name"	TEXT,
	"stop_desc"	TEXT,
	"stop_lat"	TEXT,
	"stop_lon"	TEXT,
	"zone_id"	TEXT,
	"stop_url"	TEXT,
	"location_type"	TEXT,
	"parent_station"	TEXT,
	"stop_timezone"	TEXT,
	"wheelchair_boarding"	TEXT,
	PRIMARY KEY("stop_id")
);

DROP TABLE IF EXISTS "trips";
CREATE TABLE "trips" (
	"route_id"	TEXT,
	"service_id"	TEXT,
	"trip_id"	TEXT NOT NULL,
	"trip_headsign"	TEXT,
	"trip_short_name"	TEXT,
	"direction_id"	TEXT,
	"block_id"	TEXT,
	"shape_id"	TEXT,
	"wheelchair_accessible"	TEXT,
	"bikes_allowed"	TEXT,
	PRIMARY KEY("trip_id")
);

.mode csv
.import --skip 1 'data/agency.txt' agency
.import --skip 1 'data/calendar_dates.txt' calendar_dates
.import --skip 1 'data/fare_attributes.txt' fare_attributes
.import --skip 1 'data/routes.txt' routes
.import --skip 1 'data/shapes.txt' shapes
.import --skip 1 'data/stop_times.txt' stop_times
.import --skip 1 'data/stops.txt' stops
.import --skip 1 'data/trips.txt' trips
```

- Run the following code:

```
cat load-gtfs.sql | sqlite3 buswear.db
```

## References

- https://developers.google.com/transit/gtfs?hl=en
- https://www.ctmcagliari.it/opendata/
