# StockPricesTrackerCompose

This project showcases an app which displays a bunch of hardcoded stock tickers being updated automatically through a web socket using jetpack compose.

![image](https://user-images.githubusercontent.com/19254758/143469005-3c248fa0-c97d-49e2-9d78-e3e7d6e3e31b.png)

# Tech Stack
- Kotlin
- Flow & Coroutines
- Recycler View
- Constraint Layout
- Kotlinx Serizalization
- Moshi (Not used in main branch)
- Scarlet (Not used in main branch)
- OkHttp
- Mockk
- Dagger Hilt
- Timber

# ToDos & Workflow
## Completed
- ~~Add Data Binding~~
- ~~Add Navigation & safeArgs~~
- ~~Add XML files to res folder~~
- ~~Add Hilt & set it up~~
- ~~Add RecyclerView & Adapter (Hardcode entries)~~
- ~~Bind ViewModel~~
- ~~Display Hardcoded data from the VM~~
- ~~Create mock UseCases layer~~
- ~~Inject UseCases in the VM~~
- ~~Create Repository layer~~
- ~~Inject Repository in the UseCases~~
- ~~Test ViewModels~~
- ~~Test UseCases~~
- ~~Test Repositories~~
- ~~Test Mappers~~
- ~~Test ViewModels~~
- ~~Test UseCases~~
- ~~Test Repositories~~
- ~~Test Mappers~~
- ~~Create Scarlet Service (See Scarlet ad-hoc branch)~~ Values can be collected in the VM but they do not propagate to the UI. Also unsubscribtion doesn't work.
- ~~Inject Service in the repository~~
- ~~Delete all unnecessary logs.~~
- - Modularize project

## Not yet
- Detail Screens

# Resources & Credits

## Flows
- https://manuelvivo.dev/coroutines-addrepeatingjob

## Clean Architecture
- https://medium.com/swlh/clean-architecture-in-android-a-beginner-approach-be0ce00d806b

## Web Sockets
- https://ssaurel.medium.com/learn-to-use-websockets-on-android-with-okhttp-ba5f00aea988
- https://proandroiddev.com/kotlin-channel-and-websocket-complete-example-and-why-not-flow-82090432880c

## Unit Testing
- https://proandroiddev.com/unit-testing-on-android-9c15632848c
