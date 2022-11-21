# Shopping cart implementation

## Assignment details
We will be using a simple shopping cart, similar to what you would see on any ecommerce website, as the domain for this
problem. The output of this is a single zip file containing all relevant code, tests, explanatory notes, and build
scripts. Please do not include binaries or external packages or libraries you have used.

### What we are looking for
Test Coverage: The solution should be developed “test-first”, should have good unit tests,
and common paths should be covered. Your tests should also be self-contained and not rely
on external systems to be available to run.

Simplicity: We value simplicity as an architectural virtue and a development practice.
Solutions should reflect the difficulty of the assigned task, and should not be overly complex.
Layers of abstraction, patterns, or architectural features that aren’t called for should not be
included.

Self-explanatory code: The solution you produce must speak for itself. Multiple paragraphs
explaining the solution are a sign that it isn’t straightforward enough to understand purely by
reading code, and are not appropriate.

### The Problem Statement

Create a shopping cart package that facilitates 2 basic capabilities

1. Add multiple products to the cart. A product has a name and price. You should be
able to specify how many of each product is being added to the cart and provide a
means to observe the resulting state.

2. Calculate the totals

- Cart subtotal (sum of price for all items)
- Tax payable, charged at 12.5% on the subtotal
- Total payable (subtotal + tax)

Pricing data for each product should be retrieved via an HTTP call. You can find example
pricing data for a set of sample products at the URI’s below. You should assume that the
product name (lowercased) matches the filename. Use whatever libraries you like to get and
parse the json. For example OKHttp (https://square.github.io/okhttp/) is a simple client for
Java.

- [cheerios.json](https://equalexperts.github.io/backend-take-home-test-data/cheerios.json)
- [cornflakes.json](https://equalexperts.github.io/backend-take-home-test-data/cornflakes.json)
- [frosties.json](https://equalexperts.github.io/backend-take-home-test-data/frosties.json)
- [shreddies.json](https://equalexperts.github.io/backend-take-home-test-data/shreddies.json)
- [weetabix.json](https://equalexperts.github.io/backend-take-home-test-data/weetabix.json)

### Example

```
Add 2 × cornflakes @ 2.52 each
Add 1 × weetabix @ 9.98 each
Subtotal = 15.02
Tax = 1.88
Total= 16.90
```

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
