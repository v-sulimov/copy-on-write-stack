package com.vsulimov.stack

/**
 * A thread-safe stack implementation based on the Copy-on-Write principle.
 * The stack employs immutability to avoid locks, where each modification returns a new stack instance.
 *
 * @param T The type of elements held in this stack.
 *
 * This stack is suitable for scenarios where reads are more frequent than writes,
 * as it copies the internal list of elements on each modification.
 *
 * Algorithms complexity:
 * - `push`: O(n), as it creates a new list with one additional element.
 * - `pop`: O(n), due to creating a new list without the last element.
 * - `peek`: O(1), simply accesses the last element of the list.
 * - `size`: O(1), as it returns the size of the list.
 * - `isEmpty`: O(1), checks if the list is empty.
 * - `mapInPlace`: O(n), maps each element in the internal list to a new element.
 */
class CopyOnWriteStack<T>(private val elements: List<T> = emptyList()) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CopyOnWriteStack<*>

        return elements == other.elements
    }

    override fun hashCode(): Int = elements.hashCode()

    /**
     * Creates a new stack with the specified element added to the top.
     *
     * @param element The element to be pushed onto the stack.
     * @return A new `CopyOnWriteStack` instance with the element added.
     *
     * Complexity: O(n), where n is the number of elements currently in the stack.
     */
    fun push(element: T): CopyOnWriteStack<T> = CopyOnWriteStack(elements + element)

    /**
     * Removes the top element of the stack and returns it along with a new stack instance.
     * Returns an unchanged stack if it is empty.
     *
     * @return A pair containing the popped element (or null if empty) and the new stack instance.
     *
     * Complexity: O(n), due to creating a new list without the last element.
     */
    fun pop(): Pair<T?, CopyOnWriteStack<T>> = if (elements.isEmpty()) {
        Pair(null, this)
    } else {
        Pair(elements.last(), CopyOnWriteStack(elements.dropLast(1)))
    }

    /**
     * Returns but does not remove the top element of the stack, or null if the stack is empty.
     *
     * @return The top element of the stack, or null if the stack is empty.
     *
     * Complexity: O(1), as it directly accesses the last element of the list.
     */
    fun peek(): T? = elements.lastOrNull()

    /**
     * Returns the number of elements in the stack.
     *
     * @return The size of the stack.
     *
     * Complexity: O(1), as it returns the size of the list.
     */
    fun size(): Int = elements.size

    /**
     * Checks if the stack is empty.
     *
     * @return `true` if the stack contains no elements, `false` otherwise.
     *
     * Complexity: O(1), as it checks the emptiness of the list.
     */
    fun isEmpty(): Boolean = elements.isEmpty()

    /**
     * Applies a transformation function to each element of the stack in place, returning a new stack.
     *
     * @param transform The function to apply to each element, replacing the element with its result.
     * @return A new `CopyOnWriteStack` where each element is transformed.
     *
     * Complexity: O(n), with n as the number of elements in the stack.
     */
    fun mapInPlace(transform: (T) -> T): CopyOnWriteStack<T> = CopyOnWriteStack(elements.map(transform))
}
