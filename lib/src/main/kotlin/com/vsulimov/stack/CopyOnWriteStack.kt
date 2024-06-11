package com.vsulimov.stack

import java.util.NoSuchElementException

/**
 * The [CopyOnWriteStack] class represents a last-in-first-out (LIFO) stack of objects.
 * The usual push and pop operations are provided, as well as a method to peek at the top item on the stack and
 * a method to test for whether the stack is empty.
 *
 * When a stack is first created, it contains no items.
 */
class CopyOnWriteStack<E> {

    private val elements: List<E>

    constructor() {
        elements = emptyList()
    }

    private constructor(elements: List<E>) {
        this.elements = elements
    }

    /**
     * Pushes an item onto the top of this stack.
     */
    fun push(element: E): CopyOnWriteStack<E> = CopyOnWriteStack(ArrayList(elements).apply { add(element) })

    /**
     * Removes the object at the top of this stack.
     */
    fun pop(): CopyOnWriteStack<E> {
        ensureNotEmpty()
        return CopyOnWriteStack(elements.dropLast(1))
    }

    /**
     * Looks at the object at the top of this stack without removing it from the stack.
     */
    fun peek(): E {
        ensureNotEmpty()
        return elements.last()
    }

    /**
     * Returns the first element matching the given predicate, or null if element was not found.
     */
    fun peekIf(predicate: (E) -> Boolean): E? = elements.firstOrNull(predicate)

    /**
     * Reduces each element of this stack with the result of applying the operator to that element.
     * Errors or runtime exceptions thrown by the operator are relayed to the caller.
     */
    fun reduceEach(operator: (E) -> E): CopyOnWriteStack<E> {
        val newElements = mutableListOf<E>()
        elements.forEach {
            val element = operator(it)
            newElements += element
        }
        return CopyOnWriteStack(newElements)
    }

    /**
     * Drops all elements and push given element onto the top of this stack.
     */
    fun replaceAllWith(element: E): CopyOnWriteStack<E> = CopyOnWriteStack<E>().push(element)

    /**
     * Returns the number of components in this stack.
     */
    fun size(): Int = elements.size

    /**
     * Returns true if this stack is empty.
     */
    fun isEmpty(): Boolean = elements.isEmpty()

    @Throws(NoSuchElementException::class)
    private fun ensureNotEmpty() {
        if (elements.isEmpty()) {
            throw NoSuchElementException("Stack is empty")
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CopyOnWriteStack<*>

        if (elements != other.elements) return false

        return true
    }

    override fun hashCode(): Int {
        return elements.hashCode()
    }
}
