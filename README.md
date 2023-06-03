## Version history
### 1.5-SNAPSHOT
* Introduce `Service` classes
* Move business logic from `Model` classes to `Service` classes
* Move `Model` classes to own package
* Employ `lombok` dependency to minify `Model` classes declaration 
* Add  unit tests to `Model` classes
* Improve exception handling in `Theater::reserve` and add unit tests for the exceptional cases
* Increase unit test code coverage up to 95%

### 1.4-SNAPSHOT
* Introduce `Schedule` interface to logically combine scheduled showings with schedule date.
* Introduce `StaticSchedule` class to abstract `Theater` class from in-place data.
* Call `LocalDateProvider::get` only once in `StaticSchedule` to ensure consistent date value across showings and schedule date.   

### 1.3-SNAPSHOT
* Refactor `Theater` class to improve dependency injection.
* Provide `PrintStream` dependency to `Theater` class in order to capture output.
* Update `TheaterTests`, replace `printMovieSchedule()` that did just output with `shouldPrintMovieScheduleStaticData()` which now compares captured output with the expected data.
* Change `LocalDateProvider` to extend `Supplier<LocalDate>` and avoid Singleton since Singleton injection is harder to unit test.

### 1.2-SNAPSHOT
* Configure Jacoco Gradle plugin to measure unit test coverage. Current total coverage is 68%.

### 1.1-SNAPSHOT
* Migrate build to Gradle. While Maven build is fine, Gradle provides modern features and better readability.

### 1.0-SNAPSHOT
Initial version


# Original instructions (Introduction)

This is a poorly written application, and we're expecting the candidate to greatly improve this code base.

## Instructions
* **Consider this to be your project! Feel free to make any changes**
* There are several deliberate design, code quality and test issues in the current code, they should be identified and resolved
* Implement the "New Requirements" below
* Keep it mind that code quality is very important
* Focus on testing, and feel free to bring in any testing strategies/frameworks you'd like to implement
* You're welcome to spend as much time as you like, however, we're expecting that this should take no more than 2 hours

## `movie-theater`

### Current Features
* Customer can make a reservation for the movie
  * And, system can calculate the ticket fee for customer's reservation
* Theater have a following discount rules
  * 20% discount for the special movie
  * $3 discount for the movie showing 1st of the day
  * $2 discount for the movie showing 2nd of the day
* System can display movie schedule with simple text format

## New Requirements
* New discount rules; In addition to current rules
  * Any movies showing starting between 11AM ~ 4pm, you'll get 25% discount
  * Any movies showing on 7th, you'll get 1$ discount
  * The discount amount applied only one if met multiple rules; biggest amount one
* We want to print the movie schedule with simple text & json format