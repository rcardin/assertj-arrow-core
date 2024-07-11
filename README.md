![Kotlin Version](https://img.shields.io/badge/Kotlin-2.0.0-blue?style=flat&logo=kotlin)
![GitHub Workflow Status (with branch)](https://img.shields.io/github/actions/workflow/status/rcardin/assertj-arrow-core/ci.yml?branch=main)
![Maven Central](https://img.shields.io/maven-central/v/in.rcard/assertj-arrow-core)
![GitHub release (latest by date)](https://img.shields.io/github/v/release/rcardin/assertj-arrow-core)
[![javadoc](https://javadoc.io/badge2/in.rcard/assertj-arrow-core/javadoc.svg)](https://javadoc.io/doc/in.rcard/assertj-arrow-core)
<a href="https://pinterest.github.io/ktlint/"><img src="https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg" alt="ktlint"></a>

# assertj-arrow-core

This project provides a set of [AssertJ](https://assertj.github.io/doc/) assertions for the [Arrow](https://arrow-kt.io/) library. In detail, the project provides assertions for the following Arrow types:

- [x] `Either<E, A>`
- [x] `Option<A>`
- [x] `Raise<E>.() -> A`

Maybe you're asking yourself: "Why do we need AssertJ assertions for Arrow types?". The answer is simple: We often use Kotlin and Arrow Kt inside a Java project using Spring Boot. In this case, we already have AssertJ in the classpath as an assertion library. So, why not use it to assert Arrow types?

## Usage

The library is available on Maven Central. To use it, add the following dependency to your `pom.xml` file:

```xml
<dependency>
  <groupId>in.rcard</groupId>
  <artifactId>assertj-arrow-core</artifactId>
  <version>1.0.0</version>
  <scope>test</scope>
</dependency>
```
