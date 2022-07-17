# Shopping cart implementation

## Assignment details
We will be using a simple shopping cart, similar to what you would see on any ecommerce website, as the domain for this
problem. The output of this is a single zip file containing all relevant code, tests, explanatory notes, and build
scripts. Please do not include binaries or external packages or libraries you have used.

### What other functionality is required?
No other capabilities are required. This is not a trick question and there is no single correct answer. We prefer simple, well tested solutions over clever solutions. The complexity of your solution should reflect that of the problem. Use whatever external libraries or packages you wish.

### Shall I include an app?
Please do not write a web, desktop, command line or any other kind of app. Your code needs only to be driven by tests.

### What about ambiguity?
If there is any ambiguity please add this in a section added to the bottom of the README and make a choice yourself to resolve the ambiguity.

### How should rounding be handled?
Prices should be rounded up where required.

## Dependencies

```
Java 17+
Kotlin 1.7.10+
Gradle 7.5+
```

## Executing tests

```gradle clean test```

## Implementation notes

- This codebase follows a mix of Object-Oriented Programming and Functional Programming paradigms
- It avoids mutation of objects across the entire codebase and creates new object instances when changes to state happen
- It uses a functional error handling approach to deal with errors based on the Arrow library for Kotlin
- Only basic error handling of the Product Catalogue API has been implemented, there are no API docs provided for it
- HTTP calls are blocking
- No mocking libraries or tools to fake HTTP calls have been used and the test-doubles have been implemented manually
- No frameworks or libraries for dependency injection have been used and dependencies are injected manually
- Only basic equals & hashcode tests have been implemented for the Amount value object
