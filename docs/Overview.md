# CopyOnWriteStack Overview

The `CopyOnWriteStack` class is an immutable stack implementation in Kotlin. It uses a functional approach to manage
elements, ensuring that all operations return a new instance without modifying the original.

## Core Methods

- **push(element: T): CopyOnWriteStack<T>**
    - Adds an element to the top of the stack.

- **pop(): Pair<T?, CopyOnWriteStack<T>>**
    - Removes and returns the top element along with a new stack instance.

- **peek(): T?**
    - Returns the top element without modifying the stack.

- **size(): Int**
    - Returns the number of elements in the stack.

- **isEmpty(): Boolean**
    - Checks if the stack is empty.

- **mapInPlace(transform: (T) -> T): CopyOnWriteStack<T>**
    - Applies a transformation to each element and returns a new stack.

## Design Philosophy

The design focuses on immutability, aiming for thread-safety and simplicity in functional-style programming.
