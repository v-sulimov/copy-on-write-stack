# CopyOnWriteStack

CopyOnWriteStack is an immutable stack implementation designed using the "copy-on-write" technique. It ensures that
every operation like `push`, `pop`, or transformation returns a new instance, keeping the original stack unchanged.

## Features

- Immutable stack operations (`push`, `pop`, `peek`).
- Transformation of stack elements using `mapInPlace`.
- Stack size and emptiness checks.

## Getting Started

## Installation

To add Copy-on-write stack to your project, include the following repository in your `settings.gradle.kts`:

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        // Other repositories here.
        maven {
            name = "repository"
            url = uri("https://maven.vsulimov.com/releases")
        }
    }
}
```

Then, include the following dependency in your `build.gradle.kts`:

```kotlin
dependencies {
    implementation("com.vsulimov:stack:1.0.2")
}
```

### Usage

```kotlin
val stack = CopyOnWriteStack<Int>()
val newStack = stack.push(1).push(2)

println(newStack.peek()) // Output: 2

val (top, remainingStack) = newStack.pop()
println(top) // Output: 2
println(remainingStack.peek()) // Output: 1

val doubledStack = newStack.mapInPlace { it * 2 }
// doubledStack will have 2, 4 as elements
```

### Testing

To run the tests, use Kotlin's native `kotlin.test` framework. Make sure to set up your build environment to include the
necessary dependencies for Kotlin testing.

### Contributing

To contribute to this project, please fork the repository and submit a pull request. Ensure your changes are
well-documented and include additional tests where applicable.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.
