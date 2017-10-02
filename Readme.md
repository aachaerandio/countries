## Countries

This Android app uses the REST Countries API to show a list of countries and its details. 

I architected the app following the MVP pattern, having a presenter layer that serves the data from the Domain (API) to the view. I have used Dagger 2 for dependency injection and RxJava 2 for a reactive approach.

Tested the presentation layer of the MVP, mocking out the dependencies and third party libraries like ´Retrofit´ using Mockito.

Used 3rd party libraries like:
 - Retrofit 2 (Networking)
 - Dagger 2 (dependency injection)
 - RxJava 2 (reactive programming)
 - Picasso (image loading)
 - JUnit/Mockito (testing)
 - Butterknife (binding for Android views)

### Improvements
- Add more testing.
- Show more country details.
- Custom error messages per error type. 
