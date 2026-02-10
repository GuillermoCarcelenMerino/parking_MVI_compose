This project started as a parking application that communicates with a local backend running in Docker, ensuring a reproducible development environment.

The architecture follows an MVI (Model–View–Intent) pattern with a unidirectional data flow. The UI is implemented using Jetpack Compose, where screens observe immutable state and emit user intents.

The project is split into two modules:
• App – contains UI and ViewModels
• Core – contains reusable business logic and domain functionality

The main business logic is implemented in UseCases, which are injected into ViewModels via Hilt. This improves separation of concerns and testability.

Networking is handled using Retrofit. An OkHttp interceptor and authenticator are implemented for logging and authentication purposes.

A local cache is used for in-memory temporary data (strings, booleans, etc.), while DataStore is used for non-volatile persistence.

The architecture is designed to be scalable, testable, and easy to extend with new features and modules.

Next steps:

Implement google maps sdk. 
Implement pagging for parkings listing.
Implement tests with kotest.
Implement androidtest.
