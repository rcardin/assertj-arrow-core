![Kotlin Version](https://img.shields.io/badge/Kotlin-2.0.0-blue?style=flat&logo=kotlin)
![GitHub Workflow Status (with branch)](https://img.shields.io/github/actions/workflow/status/rcardin/assertj-arrow-core/ci.yml?branch=main)
![Maven Central](https://img.shields.io/maven-central/v/in.rcard/assertj-arrow-core)
![GitHub release (latest by date)](https://img.shields.io/github/v/release/rcardin/assertj-arrow-core)
[![javadoc](https://javadoc.io/badge2/in.rcard/assertj-arrow-core/javadoc.svg)](https://javadoc.io/doc/in.rcard/assertj-arrow-core)
<a href="https://pinterest.github.io/ktlint/"><img src="https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg" alt="ktlint"></a>

# assertj-arrow-core

This project provides a set of [AssertJ](https://assertj.github.io/doc/) assertions for
the [Arrow](https://arrow-kt.io/) library. In detail, the project provides assertions for the following Arrow types:

- [x] `Either<E, A>`
- [x] `Option<A>`
- [x] `Raise<E>.() -> A`

Maybe you're asking yourself: "Why do we need AssertJ assertions for Arrow types?". The answer is simple: We often use
Kotlin and Arrow Kt inside a Java project using Spring Boot. In this case, we already have AssertJ in the classpath as
an assertion library. So, why not use it to assert Arrow types?

## Usage

The library is available on Maven Central. To use it, add the following dependency to your `pom.xml` file:

```xml

<dependency>
    <groupId>in.rcard</groupId>
    <artifactId>assertj-arrow-core</artifactId>
    <version>1.1.0</version>
    <scope>test</scope>
</dependency>
```

Otherwise, if you're using Gradle, add the following dependency to your `build.gradle.kts` file:

```kotlin
testImplementation("in.rcard:assertj-arrow-core:1.1.0")
```

## Assertions Guide

This section describes the assertions provided by the `assertj-arrow-core` library.

### `Option<A>`

Use the `in.rcard.assertj.arrowcore.OptionAssert` class as an entry point to assert `Option<A>` instances.

| Assertions           | Description                                                                                                                                               |
|----------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------|
| `isEmpty`            | Verifies that the actual `Option` is empty.                                                                                                               |
| `contains`           | Verifies that the actual `Option` contains the given value.                                                                                               |
| `containsInstanceOf` | Verifies that the actual `Option` contains a value that is an instance of the argument.                                                                   |
| `get`                | Verifies that the actual Option is not null and not empty and returns an Object assertion that allows chaining (object) assertions on the optional value. |
| `isDefined`          | Verifies that there is a value present in the actual `Option`.                                                                                            |

### `Either<E, A>`

Use the `in.rcard.assertj.arrowcore.EitherAssert` class as an entry point to assert `Either<E, A>` instances.

| Assertions                | Description                                                                                                                                                               |
|---------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `isRight`                 | Verifies that the actual `Either` is right.                                                                                                                               |
| `isLeft`                  | Verifies that the actual `Either` is left.                                                                                                                                |
| `containsOnRight`         | Verifies that the actual `Either` is `Either.Right` and contains the given value.                                                                                         |
| `containsRightInstanceOf` | Verifies that the actual right-sided `Either` contains a value that is an instance of the argument.                                                                       |
| `asRight`                 | Verifies that the actual `Either` is not `null` and contains a right-sided value and returns an `Object` assertion that allows chaining (object) assertions on the value. |
| `containsOnLeft`          | Verifies that the actual `Either` is `Either.Left` and contains the given value.                                                                                          |
| `containsLeftInstanceOf`  | Verifies that the actual left-sided `Either` contains a value that is an instance of the argument.                                                                        |
| `asLeft`                  | Verifies that the actual `Either` is not `null` and contains a left-sided value and returns an `Object` assertion that allows chaining (object) assertions on the value.  |

### `Raise<E>.() -> A`

Use the `in.rcard.assertj.arrowcore.RaiseAssert` class as an entry point to assert `Raise<E>.() -> A` instances. There
are many different entry points, all of them available boh for regular and `suspend` functions:

| Entry Point          | Description                                                                                                                 |
|----------------------|-----------------------------------------------------------------------------------------------------------------------------|
| `assertThat`         | Entry point to assert a `Raise<E>.() -> A` instance.                                                                        |
| `assertThatThrownBy` | Verifies that the function in the `Raise` context throws an exception and let chaining assertion on the thrown exception    |
| `assertThatRaisedBy` | Verifies that the function in the `Raise` context raises a logic-typed error and let chaining assertion on the raised error |

The available assertions are:

| Assertions     | Description                                                                                                                                                         |
|----------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `succeedsWith` | Verifies that the function in the `Raise` context succeeds with the given value.                                                                                    |
| `succeeds`     | Verifies that the function in the `Raise` context succeeded. No check on the value returned by the function is performed.                                           |
| `raises`       | Verifies that the function in the Raise context fails with the given error.                                                                                         |
| `fails`        | Verifies that the function in the Raise context fails, no matter the type of the logical error.                                                                     |
| `result`       | Verifies that the actual function in the `Raise` context succeeds and returns an `Object` assertion that allows chaining (object) assertions on the returned value. |
| `error`        | Verifies that the actual function in the Raise context fails and returns an Object assertion that allows chaining (object) assertions on the raised error.          |

### `NonEmptyList<A>`

Use the `in.rcard.assertj.arrowcore.NonEmptyListAssert` class as an entry point to assert `NonEmptyList<A>` instances.

| Assertions                        | Description                                                                                                                                                               |
|-----------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `shouldContain`                   | Verifies that the actual `NonEmptyList` contains the expected element.                                                                                                    |                           
| `shouldContainAll`                | Verifies that the actual `NonEmptyList` contains all the expected elements.                                                                                               |
| `shouldContainNoNulls`            | Verifies that the actual `NonEmptyList` does not contain null.                                                                                                            |
| `shouldContainOnlyNulls`          | Verifies that the actual `NonEmptyList` contains only null.                                                                                                               |
| `shouldContainNull`               | Verifies that the actual `NonEmptyList` contains null.                                                                                                                    |
| `shouldHaveDuplicates`            | Verifies that the actual `NonEmptyList` contains at least one duplicate.                                                                                                  |
| `shouldBeSingleElement`           | Verifies that the actual `NonEmptyList` has a single element which is expected element.                                                                                   |
| `shouldBeSorted`                  | Verifies that the actual `NonEmptyList` is sorted.                                                                                                                        |
