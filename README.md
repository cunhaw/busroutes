BusRoutes
=========

- Simple Android application which searches some routes and timetables of Florianópolis's public transportation.

- It uses an external Http web service (from AppGlu) to retrieve this information.

- Project is based on the Master/Detail template from the Android SDK;

Instructions
============

- Project was created using the Android Studio environment (http://developer.android.com/sdk/installing/studio.html).
It uses Gradle as the build system, and get external dependencies from Maven central.

- Eclipse+ADT support is on the roadmap (https://github.com/cunhaw/busroutes/issues/1).

- It needs an Android API 19 compatible device to run. Target a wider range of devices is on the roadmap (https://github.com/cunhaw/busroutes/issues/6)

Dependencies
============

- Retrofit is a type-safe REST client for Android and Java. Check http://square.github.io/retrofit/.
It is used to load the bus routes data from the external Web service. This library abstracts the http+thread+callback code.
Under the hood, Retrofit depends on GSON, used to convert a JSON string to an equivalent Java object.
There is an open issue (https://github.com/cunhaw/busroutes/issues/2) to check how this loading could be better decoupled from the Activity (probably using an Android Service or ContentProvider).
