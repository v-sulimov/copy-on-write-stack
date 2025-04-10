# CopyOnWriteStack API Reference

## Class: CopyOnWriteStack

**CopyOnWriteStack<T>**

### Constructors

- `CopyOnWriteStack(elements: List<T> = emptyList())`
  Initializes a new instance of the stack with the provided elements list.

### Methods

- **push(element: T): CopyOnWriteStack<T>**
    - **Description**: Returns a new stack instance with `element` added.
    - **Complexity**: O(n)

- **pop(): Pair<T?, CopyOnWriteStack<T>>**
    - **Description**: Removes and returns the top element, and provides a new stack.
    - **Complexity**: O(n)

- **peek(): T?**
    - **Description**: Retrieves the top element without removal.
    - **Complexity**: O(1)

- **size(): Int**
    - **Description**: Returns the current number of elements.
    - **Complexity**: O(1)

- **isEmpty(): Boolean**
    - **Description**: Checks if the stack contains no elements.
    - **Complexity**: O(1)

- **mapInPlace(transform: (T) -> T): CopyOnWriteStack<T>**
    - **Description**: Applies a transformation function to each element.
    - **Complexity**: O(n)
