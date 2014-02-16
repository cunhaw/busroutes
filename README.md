BusRoutes
=========

Simple Android application which searches some routes and timetables of Florianópolis's public transportation.
It uses an external Http web service (from AppGlu) to retrieve this information.

Instructions
============

Project was created using the new Android Studio (http://developer.android.com/sdk/installing/studio.html).
It uses Gradle as the build system, and get external dependencies from Maven central.
Eclipse+ADT support is on the roadmap (https://github.com/cunhaw/busroutes/issues/1).

Dependencies
============

- Retrofit is a type-safe REST client for Android and Java. Check http://square.github.io/retrofit/.
It is used to load the bus routes data from the external Web service. This library abstracts the http+thread+callback code.
There is a related issue (https://github.com/cunhaw/busroutes/issues/2) to check if this loading should be better decoupled using a Service or ContentProvider.


